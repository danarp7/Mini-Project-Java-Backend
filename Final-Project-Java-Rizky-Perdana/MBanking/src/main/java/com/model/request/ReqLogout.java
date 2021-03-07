package com.model.request;

public class ReqLogout {
    private String user_id, activity;

    public ReqLogout(String activity, String user_id) {
        this.activity = activity;
        this.user_id = user_id;
    }
}
