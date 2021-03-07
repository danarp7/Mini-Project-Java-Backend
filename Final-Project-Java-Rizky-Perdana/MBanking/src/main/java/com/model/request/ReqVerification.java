package com.model.request;

import java.sql.Date;
import java.sql.Time;

public class ReqVerification {
    private String user_id, activity, verify_code;
    private Date date_verify_code;
    private Time time_verify_code;

    public ReqVerification(String activity, String user_id, String verify_code,
                           Date date_verify_code, Time time_verify_code) {
        this.activity = activity;
        this.user_id = user_id;
        this.verify_code = verify_code;
        this.date_verify_code = date_verify_code;
        this.time_verify_code = time_verify_code;
    }

    public Date getDate_verify_code() {
        return date_verify_code;
    }

    public void setDate_verify_code(Date date_verify_code) {
        this.date_verify_code = date_verify_code;
    }

    public Time getTime_verify_code() {
        return time_verify_code;
    }

    public void setTime_verify_code(Time time_verify_code) {
        this.time_verify_code = time_verify_code;
    }
}
