package com.model.recv;

public class ChangePwdTranxRecv {
    private String user_id, activity, currently_pwd_tranx, new_pwd_tranx;

    public ChangePwdTranxRecv(String activity, String user_id, String currently_pwd_tranx,
                              String new_pwd_tranx) {
        this.activity = activity;
        this.user_id = user_id;
        this.currently_pwd_tranx = currently_pwd_tranx;
        this.new_pwd_tranx = new_pwd_tranx;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCurrently_pwd_tranx() {
        return currently_pwd_tranx;
    }

    public void setCurrently_pwd_tranx(String currently_pwd_tranx) {
        this.currently_pwd_tranx = currently_pwd_tranx;
    }

    public String getNew_pwd_tranx() {
        return new_pwd_tranx;
    }

    public void setNew_pwd_tranx(String new_pwd_tranx) {
        this.new_pwd_tranx = new_pwd_tranx;
    }
}
