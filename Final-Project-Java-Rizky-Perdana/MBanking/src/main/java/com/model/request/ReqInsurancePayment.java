package com.model.request;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class ReqInsurancePayment {
    private String user_id, pwd_tranx, activity, polis_number, insurance;
    private Date date_insurance_payment;
    private Time time_insurance_payment;
    private BigDecimal nominal_transfer;

    public ReqInsurancePayment(String activity, String insurance, String user_id, String polis_number,
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
