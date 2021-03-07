package com.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class User {
    private String full_name, gender, user_id, email, pwd_tranx;
    private String bank_status, m_banking_status, verify_code;
    private String pin_atm, m_pin, otp, m_banking_activation;
    private String phone_number, rekening_number, dc_number, nik;
    private BigDecimal balance;
    private Date birth_date, date_verify_code, date_otp;
    private Time time_verify_code, time_otp;
    private boolean m_session_active;

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

    public String getPin_atm() {
        return pin_atm;
    }

    public void setPin_atm(String pin_atm) {
        this.pin_atm = pin_atm;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getBank_status() {
        return bank_status;
    }

    public void setBank_status(String bank_status) {
        this.bank_status = bank_status;
    }

    public String getM_banking_status() {
        return m_banking_status;
    }

    public void setM_banking_status(String m_banking_status) {
        this.m_banking_status = m_banking_status;
    }

    public String getM_banking_activation() {
        return m_banking_activation;
    }

    public void setM_banking_activation(String m_banking_activation) {
        this.m_banking_activation = m_banking_activation;
    }

    public boolean getM_session_active() {
        return m_session_active;
    }

    public void setM_session_active(boolean m_session_active) {
        this.m_session_active = m_session_active;
    }

    public String getVerify_code() {
        return verify_code;
    }

    public void setVerify_code(String verify_code) {
        this.verify_code = verify_code;
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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
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

    public User() {}

    public User(String full_name, String nik, String gender, Date birth_date, String phone_number,
                String email, String dc_number, String rekening_number, String pin_atm, String user_id,
                String m_pin, String pwd_tranx, BigDecimal balance, String bank_status,
                String m_banking_status, String m_banking_activation, boolean m_session_active,
                String verify_code, Date date_verify_code, Time time_verify_code, String otp,
                Date date_otp, Time time_otp) {
        this.full_name = full_name;
        this.nik = nik;
        this.gender = gender;
        this.birth_date = birth_date;
        this.phone_number = phone_number;
        this.email = email;
        this.dc_number = dc_number;
        this.rekening_number = rekening_number;
        this.pin_atm = pin_atm;
        this.user_id = user_id;
        this.m_pin = m_pin;
        this.pwd_tranx = pwd_tranx;
        this.balance = balance;
        this.bank_status = bank_status;
        this.m_banking_status = m_banking_status;
        this.m_banking_activation = m_banking_activation;
        this.m_session_active = m_session_active;
        this.verify_code = verify_code;
        this.date_verify_code = date_verify_code;
        this.time_verify_code = time_verify_code;
        this.otp = otp;
        this.date_otp = date_otp;
        this.time_otp = time_otp;
    }
}