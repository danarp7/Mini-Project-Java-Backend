package com.model.send;

import java.math.BigDecimal;

public class PolisNumberSend {
    private String polis_number;

    public PolisNumberSend() {}

    public PolisNumberSend(String polis_number) {
        this.polis_number = polis_number;
    }

    public String getPolis_number() {
        return polis_number;
    }

    public void setPolis_number(String polis_number) {
        this.polis_number = polis_number;
    }
}
