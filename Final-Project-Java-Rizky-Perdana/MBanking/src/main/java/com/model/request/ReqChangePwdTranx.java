package com.model.request;

public class ReqChangePwdTranx {
    private String user_id, activity, currently_pwd_tranx, new_pwd_tranx;

    public ReqChangePwdTranx(String activity, String user_id, String currently_pwd_tranx,
                          String new_pwd_tranx) {
        this.activity = activity;
        this.user_id = user_id;
        this.currently_pwd_tranx = currently_pwd_tranx;
        this.new_pwd_tranx = new_pwd_tranx;
    }

    public String getNew_pwd_tranx() {
        return new_pwd_tranx;
    }

    public void setNew_pwd_tranx(String new_pwd_tranx) {
        this.new_pwd_tranx = new_pwd_tranx;
    }
}
