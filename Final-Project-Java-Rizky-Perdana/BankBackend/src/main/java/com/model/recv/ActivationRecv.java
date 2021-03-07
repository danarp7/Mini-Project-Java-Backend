package com.model.recv;

import java.sql.Date;
import java.sql.Time;

public class ActivationRecv {
    private String user_id, activity, dc_number;
    private Date date_otp;
    private Time time_otp;

    public ActivationRecv(String activity, String user_id, String dc_number,
                          Date date_otp, Time time_otp) {
        this.activity = activity;
        this.user_id = user_id;
        this.dc_number = dc_number;
        this.date_otp = date_otp;
        this.time_otp = time_otp;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDc_number() {
        return dc_number;
    }

    public void setDc_number(String dc_number) {
        this.dc_number = dc_number;
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
