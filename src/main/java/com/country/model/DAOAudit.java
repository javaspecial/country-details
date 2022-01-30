package com.country.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "audit")
public class DAOAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @JsonIgnore
    private Long userId;

    @Column
    @JsonIgnore
    private String code;

    @Column
    @JsonIgnore
    private String name;

    @Column
    @JsonIgnore
    private String symbol;

    @Column
    @JsonIgnore
    private BigDecimal exchangeRateIdr;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

}
