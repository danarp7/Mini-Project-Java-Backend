package com.model.recv;

import java.sql.Date;

public class RekeningMutationRecv {
    private String user_id, pwd_tranx, activity;
    private Date from_date_mutation, to_date_mutation;

    public RekeningMutationRecv(String activity, String user_id, Date from_date_mutation,
                               Date to_date_mutation, String pwd_tranx) {
        this.activity = activity;
        this.user_id = user_id;
        this.from_date_mutation = from_date_mutation;
        this.to_date_mutation = to_date_mutation;
        this.pwd_tranx = pwd_tranx;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getFrom_date_mutation() {
        return from_date_mutation;
    }

    public void setFrom_date_mutation(Date from_date_mutation) {
        this.from_date_mutation = from_date_mutation;
    }

    public Date getTo_date_mutation() {
        return to_date_mutation;
    }

    public void setTo_date_mutation(Date to_date_mutation) {
        this.to_date_mutation = to_date_mutation;
    }

    public String getPwd_tranx() {
        return pwd_tranx;
    }

    public void setPwd_tranx(String pwd_tranx) {
        this.pwd_tranx = pwd_tranx;
    }
}
