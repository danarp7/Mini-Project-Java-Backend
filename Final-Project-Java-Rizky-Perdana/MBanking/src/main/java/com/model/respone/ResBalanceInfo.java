package com.model.respone;

import java.math.BigDecimal;

public class ResBalanceInfo {
    private String user_id;
    private BigDecimal balance;

    public ResBalanceInfo(String user_id, BigDecimal balance) {
        this.user_id = user_id;
        this.balance = balance;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
