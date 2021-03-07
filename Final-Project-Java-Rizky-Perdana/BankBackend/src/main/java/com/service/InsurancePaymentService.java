package com.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.handler.Handler;
import com.method.Method;
import com.model.User;
import com.model.recv.InsurancePaymentRecv1;
import com.model.send.InsurancePaymentSend1;
import com.model.send.InsurancePaymentSend2;
import com.model.send.InsuranceSend;
import com.model.send.PolisNumberSend;
import com.repository.User_mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;

public class InsurancePaymentService {
    SqlSession session = null;
    User_mapper user_mapper = null;

    Handler handler = new Handler();
    Gson gson = new Gson();
    Gson gsonBuilder = new GsonBuilder().create();
    Method method = new Method();

    String tempInsurance;
    String tempInsurancePayment;

    String urlAllianzInsuranceCheck = "http://localhost:8091/AllianzInsurance/Insurance/Check";
    String urlAllianzInsurancePayment = "http://localhost:8091/AllianzInsurance/Insurance/Payment";

    public void readConfig() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(User_mapper.class);

        user_mapper = session.getMapper(User_mapper.class);
    }

    public String executePost(String targetURL, String urlParam) {
        URL url;
        HttpURLConnection con = null;

        try {
            url = new URL(targetURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = urlParam.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            StringBuilder response;
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    public String checkInsurance(String user_id, String insurance, String polis_number) throws IOException {
        readConfig();

        String report = "";

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "Check Insurance: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Registered")) {
                session.commit();
                session.close();
                return "Check Insurance: Failed - Not Registered";
            }

            if (!handler.isUserStillLoggedIn(user_id)) {
                session.commit();
                session.close();
                return "Check Insurance: Failed - User not logged in.";
            }

            switch (insurance) {
                case "Allianz":
                    PolisNumberSend polisNumberSend = new PolisNumberSend();

                    polisNumberSend.setPolis_number(polis_number);

                    tempInsurance = gson.toJson(polisNumberSend);

                    report = executePost(urlAllianzInsuranceCheck, tempInsurance);
                    break;
                default :
                    report = "Check Insurance: Failed - Please input the correct insurance";
                    break;
            }

            if (report.charAt(0) == '{') {
                InsuranceSend insuranceSend = gsonBuilder.fromJson(report, InsuranceSend.class);
                String tempInsuranceSend = gson.toJson(insuranceSend);
                return tempInsuranceSend;
            }

            session.commit();
            session.close();
            return report;
        } else {
            session.commit();
            session.close();
            return "Check Insurance: Failed - User with user id " + user_id + " not found.";
        }
    }

    public String insurancePayment(String user_id, String insurance, String polis_number,
                                   BigDecimal nominal_transfer, String pwd_tranx,
                                   Date date_insurance_payment, Time time_insurance_payment) throws IOException {
        readConfig();

        String report = "";

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "Insurance Payment: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Registered")) {
                session.commit();
                session.close();
                return "Insurance Payment: Failed - Not Registered";
            }

            if (!handler.isUserStillLoggedIn(user_id)) {
                session.commit();
                session.close();
                return "Insurance Payment: Failed - User not logged in.";
            }

            if (!pwd_tranx.equals(temp.getPwd_tranx())) {
                session.commit();
                session.close();
                return "Insurance Payment: Failed - Wrong Password";
            }

            int compare = nominal_transfer.compareTo(temp.getBalance());

            if (compare == 1) {
                session.commit();
                session.close();
                return "Insurance Payment: Failed - The balance is not sufficient";
            }

            switch (insurance) {
                case "Allianz":
                    InsurancePaymentSend1 insurancePaymentSend1 = new InsurancePaymentSend1();

                    insurancePaymentSend1.setPolis_number(polis_number);
                    insurancePaymentSend1.setNominal_transfer(nominal_transfer);
//                    insurancePaymentSend1.setDate_insurance_payment(date_insurance_payment);
//                    insurancePaymentSend1.setTime_insurance_payment(time_insurance_payment);

                    tempInsurancePayment = gson.toJson(insurancePaymentSend1);

                    report = executePost(urlAllianzInsurancePayment, tempInsurancePayment);
                    break;
                default :
                    report = "Insurance Payment: Failed - Please input the correct insurance";
                    break;
            }

            if (report.charAt(0) == '{') {
                InsurancePaymentRecv1 insurancePaymentRecv1 = gsonBuilder.fromJson(report, InsurancePaymentRecv1.class);

                InsurancePaymentSend2 insurancePaymentSend2 = new InsurancePaymentSend2();
                insurancePaymentSend2.setRekening_number(temp.getRekening_number());
                insurancePaymentSend2.setInsurance(insurancePaymentRecv1.getInsurance());
                insurancePaymentSend2.setPolis_number(insurancePaymentRecv1.getPolis_number());
                insurancePaymentSend2.setFull_name(insurancePaymentRecv1.getFull_name());
                insurancePaymentSend2.setGrade(insurancePaymentRecv1.getGrade());
                insurancePaymentSend2.setInsurance_fee(insurancePaymentRecv1.getInsurance_fee());
                insurancePaymentSend2.setStatus_fee("Success");
//                insurancePaymentSend2.setDate_insurance_payment(insurancePaymentRecv1.getDate_insurance_payment());
//                insurancePaymentSend2.setTime_insurance_payment(insurancePaymentRecv1.getTime_insurance_payment());
                insurancePaymentSend2.setDate_insurance_payment(date_insurance_payment);
                insurancePaymentSend2.setTime_insurance_payment(time_insurance_payment);

                BigDecimal tempBalance = method.tranx(temp.getBalance(), insurancePaymentRecv1.getInsurance_fee());

                user_mapper.tranx(temp.getUser_id(), tempBalance);
//                user_mapper.insertTranx(insurancePaymentRecv1.getDate_insurance_payment(),
//                                        insurancePaymentRecv1.getTime_insurance_payment(),
//                                        temp.getRekening_number(), insurancePaymentRecv1.getInsurance_fee(),
//                                        "CR", "TF to " + insurancePaymentRecv1.getInsurance() +
//                                        " " + insurancePaymentRecv1.getPolis_number());
                user_mapper.insertTranx(date_insurance_payment,
                                        time_insurance_payment,
                                        temp.getRekening_number(), insurancePaymentRecv1.getInsurance_fee(),
                                        "CR", "TF to " + insurancePaymentRecv1.getInsurance() +
                                                " " + insurancePaymentRecv1.getPolis_number());

                String tempInsuranceSend = gson.toJson(insurancePaymentSend2);
                session.commit();
                session.close();
                return tempInsuranceSend;
            }

            session.commit();
            session.close();
            return report;
        } else {
            session.commit();
            session.close();
            return "Insurance Payment: Failed - User with user id " + user_id + " not found.";
        }
    }
}
