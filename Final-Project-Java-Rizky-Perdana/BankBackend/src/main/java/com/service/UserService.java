package com.service;

import com.google.gson.Gson;
import com.handler.Handler;
import com.method.Method;
import com.model.User;
import com.model.send.ActivationSend;
import com.model.send.BalanceInfoSend;
import com.model.send.RegisterSend;
import com.model.send.SeeProfileSend;
import com.repository.User_mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;

public class UserService {
    SqlSession session = null;
    User_mapper user_mapper = null;

    Handler handler = new Handler();
    Gson gson = new Gson();
    Method method = new Method();

    public void readConfig() throws IOException {
        Reader reader = Resources.getResourceAsReader("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        session = sqlSessionFactory.openSession();
        session.getConfiguration().addMapper(User_mapper.class);

        user_mapper = session.getMapper(User_mapper.class);
    }

    public String userRegister(String rekening_number, String pin_atm, String email,
                               Date birth_date, String nik, String user_id, Date date_verify_code,
                               Time time_verify_code) throws IOException {
        readConfig();

        if (handler.isUserRekeningExist(rekening_number)) {
            User temp = user_mapper.getByRekening(rekening_number);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "Register: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Not Registered")) {
                session.commit();
                session.close();
                return "Register: Failed - You Are Already Registered";
            }

            if (!temp.getPin_atm().equals(pin_atm)) {
                session.commit();
                session.close();
                return "Register: Failed - Wrong Pin ATM";
            }

            if (!temp.getEmail().equals(email)) {
                session.commit();
                session.close();
                return "Register: Failed - Wrong Email";
            }

            if (!temp.getBirth_date().equals(birth_date)) {
                session.commit();
                session.close();
                return "Register: Failed - Wrong Birth Date";
            }

            if (!temp.getNik().equals(nik)) {
                session.commit();
                session.close();
                return "Register: Failed - Wrong NIK";
            }

            if (handler.isUserIDExist(user_id)) {
                session.commit();
                session.close();
                return "Register: Failed - User ID Is Exist. Please Change Your User ID";
            }

            String verify_code = method.getVerifyCode();
            user_mapper.register(rekening_number, user_id,
                                verify_code, date_verify_code, time_verify_code);

            RegisterSend tempRegister = new RegisterSend();
            tempRegister.setFull_name(temp.getFull_name());
            tempRegister.setRegistration_date(date_verify_code);
            tempRegister.setRegistration_time(time_verify_code);
            tempRegister.setUser_id(user_id);
            tempRegister.setEmail(temp.getEmail());
            tempRegister.setVerify_code(verify_code);

            //opsional email verify code to user

            String json = gson.toJson(tempRegister);

            session.commit();
            session.close();
            return json;
        } else {
            session.commit();
            session.close();
            return "Register: Failed - Register with rekening number " + rekening_number +
                    " not found.";
        }
    }

    public String verification(String user_id, String verify_code,
                               Date date_verify_code, Time time_verify_code) throws IOException, ParseException {
        readConfig();

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "Verification: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Not Registered")) {
                session.commit();
                session.close();
                return "Verification: Failed - You Are Already Registered";
            }

            if (!temp.getVerify_code().equals(verify_code)) {
                session.commit();
                session.close();
                return "Verification: Failed - Wrong Verification Code";
            }

            long diffTime = method.getDiffTimeSec(temp.getDate_verify_code(), temp.getTime_verify_code(),
                                        date_verify_code, time_verify_code);
            long limitTime = 5 * 60;

            if (diffTime > limitTime) {
                session.commit();
                session.close();
                return "Verification: Failed - Expired Verification Code. Please Re-Register";
            }

            user_mapper.verification(user_id, "Registered");
            session.commit();
            session.close();
            return "Verification: Success - Please Activation Before Use Mobile Banking";
        } else {
            session.commit();
            session.close();
            return "Verification: Failed - Verification with user id " + user_id + " not found.";
        }
    }

    public String activation(String user_id, String dc_number, Date date_otp,
                             Time time_otp) throws IOException, ParseException {
        readConfig();

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "Activation: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Registered")) {
                session.commit();
                session.close();
                return "Activation: Failed - Not Registered. Please Register First";
            }

            if (!temp.getM_banking_activation().equals("Not Active")) {
                session.commit();
                session.close();
                return "Activation: Failed - You Are Already Activation";
            }

            if (!temp.getDc_number().equals(dc_number)) {
                session.commit();
                session.close();
                return "Activation: Failed - Wrong Debit Card Number";
            }

            String otp = method.getOTP();
            user_mapper.activation(user_id, otp, date_otp, time_otp);

            ActivationSend tempActivation = new ActivationSend();
            tempActivation.setUser_id(user_id);
            tempActivation.setOtp(otp);

            //opsional send otp to phone number user

            String json = gson.toJson(tempActivation);

            session.commit();
            session.close();
            return json;
        } else {
            session.commit();
            session.close();
            return "Activation: Failed - Activation with user id " + user_id + " not found.";
        }
    }

    public String otpActivation(String user_id, String otp,
                                String m_pin, String pwd_tranx,
                                Date date_otp, Time time_otp) throws IOException, ParseException {
        readConfig();

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "OTP Activation: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Registered")) {
                session.commit();
                session.close();
                return "OTP Activation: Failed - Not Registered. Please Register First";
            }

            if (!temp.getM_banking_activation().equals("Not Active")) {
                session.commit();
                session.close();
                return "Activation: Failed - You Are Already Activation";
            }

            if (!temp.getOtp().equals(otp)) {
                session.commit();
                session.close();
                return "OTP Activation: Failed - Wrong OTP Code";
            }

            long diffTime = method.getDiffTimeSec(temp.getDate_otp(), temp.getTime_otp(),
                    date_otp, time_otp);
            long limitTime = 5 * 60;

            if (diffTime > limitTime) {
                session.commit();
                session.close();
                return "OTP Activation: Failed - Expired OTP Code. Please Re-Activation";
            }

            user_mapper.otpActivation(user_id, m_pin, pwd_tranx, "Active");
            session.commit();
            session.close();
            return "OTP Activation: Success";
        } else {
            session.commit();
            session.close();
            return "OTP Activation: Failed - OTP Activation with user id " + user_id + " not found.";
        }
    }

    public String userLogin(String user_id, String m_pin) throws IOException {
        readConfig();

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "Login: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Registered")) {
                session.commit();
                session.close();
                return "Login: Failed - Not Registered";
            }

            if (!temp.getM_banking_activation().equals("Active")) {
                session.commit();
                session.close();
                return "Login: Failed - You Not Activation";
            }

            if (handler.isUserStillLoggedIn(user_id)) {
                session.commit();
                session.close();
                return "Login: Failed - User with user id has been logged in. Pleases logout first.";
            }

            if (!m_pin.equals(temp.getM_pin())) {
                session.commit();
                session.close();
                return "Login: Failed - Wrong Password";
            }

            user_mapper.login(temp);

            session.commit();
            session.close();
            return "Login: Success";
        } else {
            session.commit();
            session.close();
            return "Login: Failed - User with user id " + user_id + " not found.";
        }
    }

    public String userLogout(String user_id) throws IOException {
        readConfig();

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "Logout: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Registered")) {
                session.commit();
                session.close();
                return "Logout: Failed - Not Registered";
            }

            if (!temp.getM_banking_activation().equals("Active")) {
                session.commit();
                session.close();
                return "Login: Failed - You Not Activation";
            }

            if (!handler.isUserStillLoggedIn(user_id)) {
                session.commit();
                session.close();
                return "Logout: Failed - User not logged in.";
            }

            user_mapper.logout(temp);

            session.commit();
            session.close();
            return "Logout: Success";
        } else {
            session.commit();
            session.close();
            return "Logout: Failed - User with user id " + user_id + " not found.";
        }
    }

    public String balanceInfo(String user_id) throws IOException {
        readConfig();

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "Balance Info: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Registered")) {
                session.commit();
                session.close();
                return "Balance Info: Failed - Not Registered";
            }

            if (!temp.getM_banking_activation().equals("Active")) {
                session.commit();
                session.close();
                return "Balance Info: Failed - You Not Activation";
            }

            if (!handler.isUserStillLoggedIn(user_id)) {
                session.commit();
                session.close();
                return "Balance Info: Failed - User not logged in.";
            }

            BalanceInfoSend tempBalance = new BalanceInfoSend();
            tempBalance.setUser_id(temp.getUser_id());
            tempBalance.setBalance(temp.getBalance());

            session.commit();
            session.close();
            String json = gson.toJson(tempBalance);
            return json;
        } else {
            session.commit();
            session.close();
            return "Balance Info: Failed - User with user id " + user_id + " not found.";
        }
    }

    public String seeProfile(String user_id) throws IOException {
        readConfig();

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "See Profile: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Registered")) {
                session.commit();
                session.close();
                return "See Profile: Failed - Not Registered";
            }

            if (!temp.getM_banking_activation().equals("Active")) {
                session.commit();
                session.close();
                return "See Profile: Failed - You Not Activation";
            }

            if (!handler.isUserStillLoggedIn(user_id)) {
                session.commit();
                session.close();
                return "See Profile: Failed - User not logged in.";
            }

            SeeProfileSend seeProfileSend = new SeeProfileSend();
            seeProfileSend.setFull_name(temp.getFull_name());
            seeProfileSend.setNik(temp.getNik());
            seeProfileSend.setGender(temp.getGender());
            seeProfileSend.setBirth_date(temp.getBirth_date());
            seeProfileSend.setPhone_number(temp.getPhone_number());
            seeProfileSend.setEmail(temp.getEmail());
            seeProfileSend.setDc_number(temp.getDc_number());
            seeProfileSend.setRekening_number(temp.getRekening_number());
            seeProfileSend.setUser_id(temp.getUser_id());

            session.commit();
            session.close();
            String json = gson.toJson(seeProfileSend);
            return json;
        } else {
            session.commit();
            session.close();
            return "See Profile: Failed - User with user id " + user_id + " not found.";
        }
    }

    public String changeMPIN(String user_id, String currently_m_pin,
                             String new_m_pin) throws IOException {
        readConfig();

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "Change Mobile Bangking PIN: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Registered")) {
                session.commit();
                session.close();
                return "Change Mobile Bangking PIN: Failed - Not Registered";
            }

            if (!temp.getM_banking_activation().equals("Active")) {
                session.commit();
                session.close();
                return "Change Mobile Bangking PIN: Failed - You Not Activation";
            }

            if (!handler.isUserStillLoggedIn(user_id)) {
                session.commit();
                session.close();
                return "Change Mobile Bangking PIN: Failed - User not logged in.";
            }

            if (!currently_m_pin.equals(temp.getM_pin())) {
                session.commit();
                session.close();
                return "Change Mobile Bangking PIN: Failed - Wrong Currently M PIN";
            }

            user_mapper.changeMPIN(temp.getUser_id(), new_m_pin);

            session.commit();
            session.close();
            return "Change Mobile Bangking PIN: Success";
        } else {
            session.commit();
            session.close();
            return "Change Mobile Bangking PIN: Failed - User with user id " + user_id + " not found.";
        }
    }

    public String changePwdTranx(String user_id, String currently_pwd_tranx,
                                 String new_pwd_tranx) throws IOException {
        readConfig();

        if (handler.isUserIDExist(user_id)) {
            User temp = user_mapper.getByUserID(user_id);

            if (!temp.getBank_status().equals("Active")) {
                session.commit();
                session.close();
                return "Change Password Transaction: Failed - Your Account Bank Is Block. Please Call Customer Service";
            }

            if (!temp.getM_banking_status().equals("Registered")) {
                session.commit();
                session.close();
                return "Change Password Transaction: Failed - Not Registered";
            }

            if (!temp.getM_banking_activation().equals("Active")) {
                session.commit();
                session.close();
                return "Change Password Transaction: Failed - You Not Activation";
            }

            if (!handler.isUserStillLoggedIn(user_id)) {
                session.commit();
                session.close();
                return "Change Password Transaction: Failed - User not logged in.";
            }

            if (!currently_pwd_tranx.equals(temp.getPwd_tranx())) {
                session.commit();
                session.close();
                return "Change Password Transaction: Failed - Wrong Currently Password Transaction";
            }

            user_mapper.changePwdTranx(temp.getUser_id(), new_pwd_tranx);

            session.commit();
            session.close();
            return "Change Password Transaction: Success";
        } else {
            session.commit();
            session.close();
            return "Change Password Transaction: Failed - User with user id " + user_id + " not found.";
        }
    }
}
