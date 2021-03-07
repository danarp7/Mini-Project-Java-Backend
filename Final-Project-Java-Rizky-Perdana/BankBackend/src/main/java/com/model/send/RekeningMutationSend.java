package com.model.send;

import java.math.BigDecimal;
import java.sql.Date;

public class RekeningMutationSend {
    private Date date_tranx;
    private BigDecimal nominal;
    private String type_tranx, description;

    public RekeningMutationSend() {}

    public RekeningMutationSend(Date date_tranx, BigDecimal nominal, String type_tranx, String description) {
        this.date_tranx = date_tranx;
        this.nominal = nominal;
        this.type_tranx = type_tranx;
        this.description = description;
    }
}
