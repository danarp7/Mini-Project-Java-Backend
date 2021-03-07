package com.model.recv;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class InsurancePaymentRecv1 {
    private String polis_number, grade, full_name, insurance;
    private Date date_insurance_payment;
    private Time time_insurance_payment;
    private BigDecimal insurance_fee;

    public InsurancePaymentRecv1() {

    }

    public InsurancePaymentRecv1(String insurance, String polis_number, String full_name,
                                String grade, BigDecimal insurance_fee,
                                Date date_insurance_payment, Time time_insurance_payment) {
        this.insurance = insurance;
        this.polis_number = polis_number;
        this.full_name = full_name;
        this.grade = grade;
        this.insurance_fee = insurance_fee;
        this.date_insurance_payment = date_insurance_payment;
        this.time_insurance_payment = time_insurance_payment;
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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public BigDecimal getInsurance_fee() {
        return insurance_fee;
    }

    public void setInsurance_fee(BigDecimal insurance_fee) {
        this.insurance_fee = insurance_fee;
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
