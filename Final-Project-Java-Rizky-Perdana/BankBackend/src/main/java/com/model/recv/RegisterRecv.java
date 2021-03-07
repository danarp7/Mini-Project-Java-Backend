package com.model.recv;

import java.sql.Date;
import java.sql.Time;

public class RegisterRecv {
    private String user_id, email, activity, rekening_number, pin_atm, nik;
    private Date birth_date, date_verify_code;
    private Time time_verify_code;

    public RegisterRecv(String activity, String rekening_number, String pin_atm, String email, Date birth_date,
                        String nik, String user_id, Date date_verify_code, Time time_verify_code) {
        this.activity = activity;
        this.rekening_number = rekening_number;
        this.pin_atm = pin_atm;
        this.email = email;
        this.birth_date = birth_date;
        this.nik = nik;
        this.user_id = user_id;
        this.date_verify_code = date_verify_code;
        this.time_verify_code = time_verify_code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRekening_number() {
        return rekening_number;
    }

    public void setRekening_number(String rekening_number) {
        this.rekening_number = rekening_number;
    }

    public String getPin_atm() {
        return pin_atm;
    }

    public void setPin_atm(String pin_atm) {
        this.pin_atm = pin_atm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
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
