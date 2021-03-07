package com.model.send;

import java.sql.Date;
import java.sql.Time;

public class RegisterSend {
    private String full_name, user_id, email, verify_code;
    private Date registration_date;
    private Time registration_time;

    public RegisterSend() {}

    public RegisterSend(String full_name, Date registration_date, Time registration_time,
                        String user_id, String email, String verify_code) {
        this.full_name = full_name;
        this.registration_date = registration_date;
        this.registration_time = registration_time;
        this.user_id = user_id;
        this.email = email;
        this.verify_code = verify_code;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
    }

    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    public Time getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(Time registration_time) {
        this.registration_time = registration_time;
    }
}
