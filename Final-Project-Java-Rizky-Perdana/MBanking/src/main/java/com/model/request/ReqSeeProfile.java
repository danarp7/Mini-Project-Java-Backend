package com.model.request;

public class ReqSeeProfile {
    private String user_id, activity;

    public ReqSeeProfile(String activity, String user_id) {
        this.activity = activity;
        this.user_id = user_id;
    }
}
