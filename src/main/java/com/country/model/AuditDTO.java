package com.country.model;

import java.math.BigDecimal;

public class AuditDTO {

    private String username;
    private String code;
    private String name;
    private String symbol;
    private String error;
    private BigDecimal exchangeRateIdr;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getExchangeRateIdr() {
        return exchangeRateIdr;
    }

    public void setExchangeRateIdr(BigDecimal exchangeRateIdr) {
        this.exchangeRateIdr = exchangeRateIdr;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
