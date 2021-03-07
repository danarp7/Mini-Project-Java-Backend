package com.handler;

import java.util.regex.Pattern;

public class Handler {
    public boolean userIDRegex(String user_id) throws Exception {
        boolean regexUserID = Pattern.matches("^[a-zA-Z0-9_-]{6,14}$", user_id);
        return regexUserID;
    }

    public boolean otpRegex(String otp) throws Exception {
        boolean regexOTP = Pattern.matches("^[0-9]{6}$", otp);
        return regexOTP;
    }

    public boolean mPINRegex(String m_pin) throws Exception {
        boolean regexMPIN = Pattern.matches("^[0-9]{6}$", m_pin);
        return regexMPIN;
    }

    public boolean pwdTranxRegex(String pwd_tranx) throws Exception {
        boolean regexPWDTranx = Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{6,14}$", pwd_tranx);
        return regexPWDTranx;
    }
}
