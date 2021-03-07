package com.model.respone;

import java.math.BigDecimal;

public class ResInsurance {
    private String polis_number, insurance, full_name, grade;
    private BigDecimal insurance_fee;

    public ResInsurance(String insurance, String polis_number, String full_name,
                        String grade, BigDecimal insurance_fee) {
        this.insurance = insurance;
        this.polis_number = polis_number;
        this.full_name = full_name;
        this.grade = grade;
        this.insurance_fee = insurance_fee;
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
}
