package com.model.respone;

import java.math.BigDecimal;
import java.sql.Date;

public class ResRekeningMutation {
    private Date date_tranx;
    private BigDecimal nominal;
    private String type_tranx, description;

    public ResRekeningMutation(Date date_tranx, BigDecimal nominal, String type_tranx, String description) {
        this.date_tranx = date_tranx;
        this.nominal = nominal;
        this.type_tranx = type_tranx;
        this.description = description;
    }

    public Date getDate_tranx() {
        return date_tranx;
    }

    public void setDate_tranx(Date date_tranx) {
        this.date_tranx = date_tranx;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    public String getType_tranx() {
        return type_tranx;
    }

    public void setType_tranx(String type_tranx) {
        this.type_tranx = type_tranx;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
