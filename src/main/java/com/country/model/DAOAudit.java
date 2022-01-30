package com.country.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "audit")
public class DAOAudit {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @JsonIgnore
    private String username;

    @Column
    private String code;

    @Column
    private String name;

    @Column
    private String symbol;

    @Column
    private BigDecimal exchangeRateIdr;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
