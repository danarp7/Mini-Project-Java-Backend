package com.model.request;

public class ReqChangeMPIN {
    private String user_id, activity, currently_m_pin, new_m_pin;

    public ReqChangeMPIN(String activity, String user_id, String currently_m_pin,
                             String new_m_pin) {
        this.activity = activity;
        this.user_id = user_id;
        this.currently_m_pin = currently_m_pin;
        this.new_m_pin = new_m_pin;
    }

    public String getNew_m_pin() {
        return new_m_pin;
    }

    public void setNew_m_pin(String new_m_pin) {
        this.new_m_pin = new_m_pin;
    }
}
