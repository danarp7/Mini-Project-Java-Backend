package com.controller;

import com.handler.Handler;
import com.model.User;
import com.model.recv.InsurancePaymentRecv;
import com.model.recv.PolisNumberRecv;
import com.model.send.InsurancePaymentSend;
import com.model.send.InsuranceSend;
import com.repository.AllianzUser_mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Date;


@RestController
@RequestMapping("/AllianzInsurance")
public class BEController {

    Gson gson = new Gson();
    Handler handler = new Handler();

    @PostMapping("/Insurance/Check")
    public String insuranceCheck(@RequestBody PolisNumberRecv polisNumberRecv) throws Exception {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(AllianzUser_mapper.class);
        AllianzUser_mapper allianzUser_mapper = session.getMapper(AllianzUser_mapper.class);

        if (!handler.isPolisNumberExist(polisNumberRecv.getPolis_number())) {
            session.commit();
            session.close();
            return "Check Insurance: Failed - Polis Number Not Found";
        }

        User temp = allianzUser_mapper.getByPolisNumber(polisNumberRecv.getPolis_number());
        InsuranceSend insuranceSend = new InsuranceSend();
        insuranceSend.setInsurance("Allianz");
        insuranceSend.setPolis_number(temp.getPolis_number());
        insuranceSend.setFull_name(temp.getFull_name());
        insuranceSend.setGrade(temp.getGrade());

        switch (temp.getGrade()) {
            case "Bronze":
                insuranceSend.setInsurance_fee(new BigDecimal(25000));
                break;
            case "Silver":
                insuranceSend.setInsurance_fee(new BigDecimal(50000));
                break;
            case "Gold":
                insuranceSend.setInsurance_fee(new BigDecimal(75000));
                break;
        }

        String json = gson.toJson(insuranceSend);

        session.commit();
        session.close();
        return json;
    }

    @PostMapping("/Insurance/Payment")
    public String insurancePayment(@RequestBody InsurancePaymentRecv insurancePaymentRecv) throws Exception {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(AllianzUser_mapper.class);
        AllianzUser_mapper allianzUser_mapper = session.getMapper(AllianzUser_mapper.class);

        if (!handler.isPolisNumberExist(insurancePaymentRecv.getPolis_number())) {
            session.commit();
            session.close();
            return "Insurance Payment: Failed - Polis Number Not Found";
        }

        User temp = allianzUser_mapper.getByPolisNumber(insurancePaymentRecv.getPolis_number());
        InsurancePaymentSend insurancePaymentSend = new InsurancePaymentSend();
        insurancePaymentSend.setInsurance("Allianz");
        insurancePaymentSend.setPolis_number(temp.getPolis_number());
        insurancePaymentSend.setFull_name(temp.getFull_name());
        insurancePaymentSend.setGrade(temp.getGrade());
//        insurancePaymentSend.setDate_insurance_payment(insurancePaymentRecv.getDate_insurance_payment());
//        insurancePaymentSend.setTime_insurance_payment(insurancePaymentRecv.getTime_insurance_payment());

        switch (temp.getGrade()) {
            case "Bronze":
                insurancePaymentSend.setInsurance_fee(new BigDecimal(25000));
                break;
            case "Silver":
                insurancePaymentSend.setInsurance_fee(new BigDecimal(50000));
                break;
            case "Gold":
                insurancePaymentSend.setInsurance_fee(new BigDecimal(75000));
                break;
        }

        int compare = insurancePaymentRecv.getNominal_transfer().compareTo(insurancePaymentSend.getInsurance_fee());

        if (compare != 0) {
            session.commit();
            session.close();
            return "Insurance Payment: Failed - Transfer nominal is not the same as the total insurance fee";
        }

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        java.sql.Time sqlTime = new java.sql.Time(utilDate.getTime());

        allianzUser_mapper.insertTranx(sqlDate, sqlTime,
                                        insurancePaymentSend.getPolis_number(),
                                        insurancePaymentRecv.getNominal_transfer(),
                                        insurancePaymentSend.getGrade());
//        allianzUser_mapper.insertTranx(insurancePaymentSend.getPolis_number(),
//                                        insurancePaymentRecv.getNominal_transfer(),
//                                        insurancePaymentSend.getGrade());

        String json = gson.toJson(insurancePaymentSend);

        session.commit();
        session.close();
        return json;
    }
}

