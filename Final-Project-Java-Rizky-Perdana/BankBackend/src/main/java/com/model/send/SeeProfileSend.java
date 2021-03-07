package com.model.send;

import java.sql.Date;

public class SeeProfileSend {
    private String full_name, gender, user_id, email;
    private String phone_number, rekening_number, dc_number, nik;
    private Date birth_date;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDc_number() {
        return dc_number;
    }

    public void setDc_number(String dc_number) {
        this.dc_number = dc_number;
    }

    public String getRekening_number() {
        return rekening_number;
    }

    public void setRekening_number(String rekening_number) {
        this.rekening_number = rekening_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public SeeProfileSend() {}

    public SeeProfileSend(String full_name, String nik, String gender, Date birth_date, String phone_number,
                          String email, String dc_number, String rekening_number, String user_id) {
        this.full_name = full_name;
        this.nik = nik;
        this.gender = gender;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.email = email;
        this.dc_number = dc_number;
        this.rekening_number = rekening_number;
        this.user_id = user_id;
    }
}
