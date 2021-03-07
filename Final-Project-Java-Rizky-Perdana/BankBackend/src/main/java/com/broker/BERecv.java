package com.broker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.recv.*;
import com.rabbitmq.client.*;
import com.service.InsurancePaymentService;
import com.service.RekeningMutationService;
import com.service.UserService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.concurrent.TimeoutException;

public class BERecv {
    private final static String EXCHANGE_NAME = "request";

    public void recvFromAPI() throws IOException, TimeoutException {
        final BESend beSend = new BESend();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //durable true
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout", true);
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        channel.basicQos(1);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] BERecv '" + message + "'");
            try {
                String respone = execute(message);
                beSend.sendToAPI(respone);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        //autoack true
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }

    private static String execute(String message) throws ParseException, IOException, java.text.ParseException {
        UserService userService = new UserService();
        RekeningMutationService rekeningMutationService = new RekeningMutationService();
        InsurancePaymentService insurancePaymentService = new InsurancePaymentService();

        Gson gsonBuilder = new GsonBuilder().create();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(message);
        JSONObject jsonInput = (JSONObject) obj;

        String report = "";
        String user_id;
        String m_pin;
        String dc_number;
        String verify_code;
        String rekening_number;
        String pin_atm;
        String email;
        String nik;
        String otp;
        String pwd_tranx;
        String insurance;
        String currently_pwd_tranx;
        String new_pwd_tranx;
        String currently_m_pin;
        String new_m_pin;
        BigDecimal nominal_transfer;
        String polis_number;
        Date birth_date;
        Date from_date_mutation;
        Date to_date_mutation;
        Date date_verify_code;
        Time time_verify_code;
        Date date_otp;
        Time time_otp;
        Date date_insurance_payment;
        Time time_insurance_payment;
        BigDecimal insurance_fee;

        String sActivity = (String) jsonInput.get("activity");

        switch (sActivity) {
            case "register":
                RegisterRecv registerRecv = gsonBuilder.fromJson(message, RegisterRecv.class);

                rekening_number = registerRecv.getRekening_number();
                pin_atm = registerRecv.getPin_atm();
                email = registerRecv.getEmail();
                birth_date = registerRecv.getBirth_date();
                nik = registerRecv.getNik();
                user_id = registerRecv.getUser_id();
                date_verify_code = registerRecv.getDate_verify_code();
                time_verify_code = registerRecv.getTime_verify_code();
                report = userService.userRegister(rekening_number, pin_atm, email, birth_date, nik,
                                                user_id, date_verify_code, time_verify_code);
                break;
            case "verification" :
                VerificationRecv verificationRecv = gsonBuilder.fromJson(message, VerificationRecv.class);

                user_id = verificationRecv.getUser_id();
                verify_code = verificationRecv.getVerify_code();
                date_verify_code = verificationRecv.getDate_verify_code();
                time_verify_code = verificationRecv.getTime_verify_code();
                report = userService.verification(user_id, verify_code,
                                                    date_verify_code, time_verify_code);
                break;
            case "activation" :
                ActivationRecv activationRecv = gsonBuilder.fromJson(message, ActivationRecv.class);

                user_id = activationRecv.getUser_id();
                dc_number = activationRecv.getDc_number();
                date_otp = activationRecv.getDate_otp();
                time_otp = activationRecv.getTime_otp();
                report = userService.activation(user_id, dc_number, date_otp, time_otp);
                break;
            case "otp activation" :
                OTPActivationRecv otpActivationRecv = gsonBuilder.fromJson(message, OTPActivationRecv.class);

                user_id = otpActivationRecv.getUser_id();
                otp = otpActivationRecv.getOtp();
                m_pin = otpActivationRecv.getM_pin();
                pwd_tranx = otpActivationRecv.getPwd_tranx();
                date_otp = otpActivationRecv.getDate_otp();
                time_otp = otpActivationRecv.getTime_otp();
                report = userService.otpActivation(user_id, otp, m_pin, pwd_tranx,
                                                    date_otp, time_otp);
                break;
            case "see profile":
                user_id = (String) jsonInput.get("user_id");
                report = userService.seeProfile(user_id);
                break;
            case "change pwd tranx":
                ChangePwdTranxRecv changePwdTranxRecv = gsonBuilder.fromJson(message, ChangePwdTranxRecv.class);

                user_id = changePwdTranxRecv.getUser_id();
                currently_pwd_tranx = changePwdTranxRecv.getCurrently_pwd_tranx();
                new_pwd_tranx = changePwdTranxRecv.getNew_pwd_tranx();
                report = userService.changePwdTranx(user_id, currently_pwd_tranx, new_pwd_tranx);
                break;
            case "change m pin":
                ChangeMPINRecv changeMPINRecv = gsonBuilder.fromJson(message, ChangeMPINRecv.class);

                user_id = changeMPINRecv.getUser_id();
                currently_m_pin = changeMPINRecv.getCurrently_m_pin();
                new_m_pin = changeMPINRecv.getNew_m_pin();
                report = userService.changeMPIN(user_id, currently_m_pin, new_m_pin);
                break;
            case "login":
                user_id = (String) jsonInput.get("user_id");
                m_pin = (String) jsonInput.get("m_pin");
                report = userService.userLogin(user_id, m_pin);
                break;
            case "logout" :
                user_id = (String) jsonInput.get("user_id");
                report = userService.userLogout(user_id);
                break;
            case "balance info" :
                user_id = (String) jsonInput.get("user_id");
                report = userService.balanceInfo(user_id);
                break;
            case "rekening mutation" :
                RekeningMutationRecv rekeningMutationRecv = gsonBuilder.fromJson(message, RekeningMutationRecv.class);

                user_id = rekeningMutationRecv.getUser_id();
                from_date_mutation = rekeningMutationRecv.getFrom_date_mutation();
                to_date_mutation = rekeningMutationRecv.getTo_date_mutation();
                pwd_tranx = rekeningMutationRecv.getPwd_tranx();

                report = rekeningMutationService.rekeningMutation(user_id, from_date_mutation,
                                                                to_date_mutation, pwd_tranx);
                break;
            case "insurance" :
                InsuranceRecv insuranceRecv = gsonBuilder.fromJson(message, InsuranceRecv.class);

                user_id = insuranceRecv.getUser_id();
                insurance = insuranceRecv.getInsurance();
                polis_number = insuranceRecv.getPolis_number();
                report = insurancePaymentService.checkInsurance(user_id, insurance, polis_number);
                break;
            case "insurance payment" :
                InsurancePaymentRecv2 insurancePaymentRecv = gsonBuilder.fromJson(message, InsurancePaymentRecv2.class);

                user_id = insurancePaymentRecv.getUser_id();
                insurance = insurancePaymentRecv.getInsurance();
                polis_number = insurancePaymentRecv.getPolis_number();
                nominal_transfer = insurancePaymentRecv.getNominal_transfer();
                pwd_tranx = insurancePaymentRecv.getPwd_tranx();
                date_insurance_payment = insurancePaymentRecv.getDate_insurance_payment();
                time_insurance_payment = insurancePaymentRecv.getTime_insurance_payment();
                report = insurancePaymentService.insurancePayment(user_id, insurance, polis_number, nominal_transfer,
                                                                    pwd_tranx, date_insurance_payment,
                                                                    time_insurance_payment);
                break;
            default :
                report = "Please input the correct activity";
                break;
        }
        return report;
    }
}
