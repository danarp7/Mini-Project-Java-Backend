package com.model.request;

public class ReqLogin {
    private String user_id, activity, m_pin;

    public ReqLogin(String activity, String user_id, String m_pin) {
        this.activity = activity;
        this.user_id = user_id;
        this.m_pin = m_pin;
    }

    public String getM_pin() {
        return m_pin;
    }

    public void setM_pin(String m_pin) {
        this.m_pin = m_pin;
    }
}
