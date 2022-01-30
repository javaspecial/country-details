package com.country.model;

import java.math.BigDecimal;

public class AuditDTO {

    private Long userId;
    private String code;
    private String name;
    private String symbol;
    private BigDecimal exchangeRateIdr;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
