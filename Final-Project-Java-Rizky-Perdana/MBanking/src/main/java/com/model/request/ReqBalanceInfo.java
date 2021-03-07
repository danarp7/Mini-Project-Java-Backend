package com.model.request;

public class ReqBalanceInfo {
    private String user_id, activity;

    public ReqBalanceInfo(String activity, String user_id) {
        this.activity = activity;
        this.user_id = user_id;
    }
}
