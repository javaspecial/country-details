package com.country.utils;

import com.country.model.AuditDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Component
public class CountryUtils implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    public static AuditDTO captureAuditOnly(Map countryDetails){
        Map details = (Map) countryDetails.get("currencies");
        AuditDTO audit = new AuditDTO();

        if (details != null && details.size() != 0) {
            audit.setCode(details.get("code").toString());
            audit.setName(details.get("name").toString());
            audit.setSymbol(details.get("symbol").toString());
            audit.setExchangeRateIdr((BigDecimal) details.get("rateIdr"));
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> authenticationMap = new ObjectMapper().convertValue(authentication.getPrincipal(), Map.class);
        audit.setUsername(authenticationMap.get("username").toString());

        return audit;
    }

    public static String getJsonData(Map containerMap) {
        String response = "";
        try{
            ObjectMapper mapper = new ObjectMapper();

            response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(containerMap);

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
        }
        return response;
    }
}
