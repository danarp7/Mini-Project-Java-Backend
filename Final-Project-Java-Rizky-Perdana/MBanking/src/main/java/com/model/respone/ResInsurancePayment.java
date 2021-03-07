package com.model.respone;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class ResInsurancePayment {
    private String status_fee, polis_number, insurance, grade, rekening_number, full_name;
    private Date date_insurance_payment;
    private Time time_insurance_payment;
    private BigDecimal insurance_fee;

    public ResInsurancePayment(String rekening_number, String insurance, String polis_number, String full_name,
                               String grade, BigDecimal insurance_fee, String status_fee,
                               Date date_insurance_payment, Time time_insurance_payment) {
        this.rekening_number = rekening_number;
        this.insurance = insurance;
        this.polis_number = polis_number;
        this.full_name = full_name;
        this.grade = grade;
        this.insurance_fee = insurance_fee;
        this.status_fee = status_fee;
        this.date_insurance_payment = date_insurance_payment;
        this.time_insurance_payment = time_insurance_payment;
    }

    public String getRekening_number() {
        return rekening_number;
    }

    public void setRekening_number(String rekening_number) {
        this.rekening_number = rekening_number;
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

    public String getStatus_fee() {
        return status_fee;
    }

    public void setStatus_fee(String status_fee) {
        this.status_fee = status_fee;
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
