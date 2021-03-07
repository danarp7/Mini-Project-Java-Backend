package com.model.recv;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class InsurancePaymentRecv2 {
    private String user_id, pwd_tranx, activity, polis_number, insurance;
    private Date date_insurance_payment;
    private Time time_insurance_payment;
    private BigDecimal nominal_transfer;

    public InsurancePaymentRecv2(String activity, String insurance, String user_id, String polis_number,
                                 BigDecimal nominal_transfer,
                                 String pwd_tranx, Date date_insurance_payment, Time time_insurance_payment) {
        this.activity = activity;
        this.user_id = user_id;
        this.insurance = insurance;
        this.polis_number = polis_number;
        this.nominal_transfer = nominal_transfer;
        this.pwd_tranx = pwd_tranx;
        this.date_insurance_payment = date_insurance_payment;
        this.time_insurance_payment = time_insurance_payment;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getPolis_number() {
        return polis_number;
    }

    public void setPolis_number(String polis_number) {
        this.polis_number = polis_number;
    }

    public BigDecimal getNominal_transfer() {
        return nominal_transfer;
    }

    public void setNominal_transfer(BigDecimal nominal_transfer) {
        this.nominal_transfer = nominal_transfer;
    }

    public String getPwd_tranx() {
        return pwd_tranx;
    }

    public void setPwd_tranx(String pwd_tranx) {
        this.pwd_tranx = pwd_tranx;
    }

    public Date getDate_insurance_payment() {
        return date_insurance_payment;
    }

    public void setDate_insurance_payment(Date date_insurance_payment) {
        this.date_insurance_payment = date_insurance_payment;
    }

    public Time getTime_insurance_payment() {
        return time_insurance_payment;
    }

    public void setTime_insurance_payment(Time time_insurance_payment) {
        this.time_insurance_payment = time_insurance_payment;
    }
}
