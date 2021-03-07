package com.model.request;

import java.sql.Date;
import java.sql.Time;

public class ReqRekeningMutation {
    private String user_id, pwd_tranx, activity;
    private Date from_date_mutation, to_date_mutation;

    public ReqRekeningMutation(String activity, String user_id, Date from_date_mutation,
                               Date to_date_mutation, String pwd_tranx) {
        this.activity = activity;
        this.user_id = user_id;
        this.from_date_mutation = from_date_mutation;
        this.to_date_mutation = to_date_mutation;
        this.pwd_tranx = pwd_tranx;
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
}
