package com.model;

import java.sql.Date;

public class User {
    private String full_name, gender, email;
    private String phone_number, grade, polis_number, nik;
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

    public String getPolis_number() {
        return polis_number;
    }

    public void setPolis_number(String polis_number) {
        this.polis_number = polis_number;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public User() {}

    public User(String full_name, String nik, String gender, Date birth_date, String phone_number,
                String email, String polis_number, String grade) {
        this.full_name = full_name;
        this.nik = nik;
        this.gender = gender;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.email = email;
        this.polis_number = polis_number;
        this.grade = grade;
    }
}