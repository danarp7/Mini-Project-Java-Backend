package com.model.send;

public class ActivationSend {
    private String user_id, otp;

    public ActivationSend() {}

    public ActivationSend(String user_id, String otp) {
        this.user_id = user_id;
        this.otp = otp;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
