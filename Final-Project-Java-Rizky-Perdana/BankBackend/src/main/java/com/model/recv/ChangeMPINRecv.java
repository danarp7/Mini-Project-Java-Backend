package com.model.recv;

public class ChangeMPINRecv {
    private String user_id, activity, currently_m_pin, new_m_pin;

    public ChangeMPINRecv(String activity, String user_id, String currently_m_pin,
                          String new_m_pin) {
        this.activity = activity;
        this.user_id = user_id;
        this.currently_m_pin = currently_m_pin;
        this.new_m_pin = new_m_pin;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCurrently_m_pin() {
        return currently_m_pin;
    }

    public void setCurrently_m_pin(String currently_m_pin) {
        this.currently_m_pin = currently_m_pin;
    }

    public String getNew_m_pin() {
        return new_m_pin;
    }

    public void setNew_m_pin(String new_m_pin) {
        this.new_m_pin = new_m_pin;
    }
}
