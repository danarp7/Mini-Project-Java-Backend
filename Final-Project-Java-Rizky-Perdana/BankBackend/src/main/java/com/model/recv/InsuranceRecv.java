package com.model.recv;

public class InsuranceRecv {
    private String user_id, activity, polis_number, insurance;

    public InsuranceRecv(String activity, String user_id, String insurance, String polis_number) {
        this.activity = activity;
        this.user_id = user_id;
        this.insurance = insurance;
        this.polis_number = polis_number;
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
}
