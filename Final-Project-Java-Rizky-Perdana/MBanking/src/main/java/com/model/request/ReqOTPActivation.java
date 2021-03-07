package com.model.request;

import java.sql.Date;
import java.sql.Time;

public class ReqOTPActivation {
    private String user_id, activity, otp, m_pin, pwd_tranx;
    private Date date_otp;
    private Time time_otp;

    public ReqOTPActivation(String activity, String user_id, String otp, String m_pin,
                            String pwd_tranx, Date date_otp, Time time_otp) {
        this.activity = activity;
        this.user_id = user_id;
        this.otp = otp;
        this.m_pin = m_pin;
        this.pwd_tranx = pwd_tranx;
        this.date_otp = date_otp;
        this.time_otp = time_otp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getM_pin() {
        return m_pin;
    }

    public void setM_pin(String m_pin) {
        this.m_pin = m_pin;
    }

    public String getPwd_tranx() {
        return pwd_tranx;
    }

    public void setPwd_tranx(String pwd_tranx) {
        this.pwd_tranx = pwd_tranx;
    }

    public Date getDate_otp() {
        return date_otp;
    }

    public void setDate_otp(Date date_otp) {
        this.date_otp = date_otp;
    }

    public Time getTime_otp() {
        return time_otp;
    }

    public void setTime_otp(Time time_otp) {
        this.time_otp = time_otp;
    }
}
