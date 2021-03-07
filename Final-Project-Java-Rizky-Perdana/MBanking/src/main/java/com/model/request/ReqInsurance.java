package com.model.request;

public class ReqInsurance {
    private String user_id, activity, polis_number, insurance;

    public ReqInsurance(String activity, String user_id, String insurance, String polis_number) {
        this.activity = activity;
        this.user_id = user_id;
        this.insurance = insurance;
        this.polis_number = polis_number;
    }
}
