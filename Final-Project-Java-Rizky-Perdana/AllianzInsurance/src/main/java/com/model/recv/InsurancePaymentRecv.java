package com.model.recv;

import java.math.BigDecimal;
//import java.sql.Date;
//import java.sql.Time;

public class InsurancePaymentRecv {
    private String polis_number;
//    private Date date_insurance_payment;
//    private Time time_insurance_payment;
    private BigDecimal nominal_transfer;

//    public InsurancePaymentRecv(String polis_number,
//                                BigDecimal nominal_transfer,
//                                Date date_insurance_payment, Time time_insurance_payment) {
//        this.polis_number = polis_number;
//        this.nominal_transfer = nominal_transfer;
//        this.date_insurance_payment = date_insurance_payment;
//        this.time_insurance_payment = time_insurance_payment;
//    }

    public InsurancePaymentRecv(String polis_number,
                                BigDecimal nominal_transfer) {
        this.polis_number = polis_number;
        this.nominal_transfer = nominal_transfer;
    }

    public InsurancePaymentRecv() {

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

//    public Date getDate_insurance_payment() {
//        return date_insurance_payment;
//    }
//
//    public void setDate_insurance_payment(Date date_insurance_payment) {
//        this.date_insurance_payment = date_insurance_payment;
//    }
//
//    public Time getTime_insurance_payment() {
//        return time_insurance_payment;
//    }
//
//    public void setTime_insurance_payment(Time time_insurance_payment) {
//        this.time_insurance_payment = time_insurance_payment;
//    }
}
