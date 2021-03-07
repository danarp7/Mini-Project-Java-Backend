package com.model.request;

import java.sql.Date;
import java.sql.Time;

public class ReqActivation {
    private String user_id, activity, dc_number;
    private Date date_otp;
    private Time time_otp;

    public ReqActivation(String activity, String user_id, String dc_number,
                         Date date_otp, Time time_otp) {
        this.activity = activity;
        this.user_id = user_id;
        this.dc_number = dc_number;
        this.date_otp = date_otp;
        this.time_otp = time_otp;
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
