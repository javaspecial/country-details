package com.country.connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CountryConnector implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    public static Map getExchangeRate(String code) {
        Map rateIDR = new HashMap();

        try {
            if (code != null) {
                String API_KEY = "63e8ac7e6e45774d3f9c2ea828c33a42";
                String requestURL = "http://data.fixer.io/api/latest?access_key=" + API_KEY + "&base=" + code + "" + "&symbols=IDR";
                String method = "GET", data = "", dataType = JavaUrlConnector.DATA_TYPE_JSON;
                Map headers = new HashMap();

                Map responseMap = JavaUrlConnector.callUrl(requestURL, method, data, dataType, headers);
                HashMap<String, Object> currencyMap = new ObjectMapper().readValue(responseMap.get("response").toString(), HashMap.class);

                rateIDR.put("success", currencyMap.get("success"));

                if ((Boolean) currencyMap.get("success")) {
                    Map rateIdr = (Map) currencyMap.get("rates");
                    rateIDR.put("rateIdr", rateIdr.get("IDR"));
                }
                else {
                    Map error = (Map) currencyMap.get("error");
                    rateIDR.put("error", error.get("type"));
                }
            }
        }
        catch (Exception e) {
            rateIDR.put("error", "Currency details loading error occurred");
            System.out.println(e.getMessage());
        }
        return rateIDR;
    }

    public static Map getCountryDetails(String name) {
        Map response = new HashMap();
        try {
            String requestURL = "https://restcountries.com/v2/name/"+name+"?fullText=true";
            String method = "GET", data = "", dataType = JavaUrlConnector.DATA_TYPE_JSON;
            Map headers = new HashMap();

            Map responseMap = JavaUrlConnector.callUrl(requestURL, method, data, dataType, headers);

            ObjectMapper mapper = new ObjectMapper();
            Object jsonObject = new JSONArray(responseMap.get("response").toString()).get(0);
            HashMap<String, Object> countryMap = mapper.readValue(((JSONObject) jsonObject).toString(), HashMap.class);

            List<Map<String, Object>> currencies = (ArrayList) countryMap.get("currencies");

            Map currencyWithITR = new HashMap();

            if (currencies != null && currencies.size() != 0) {
                for (Map<String, Object> map : currencies) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();

                        if (key == "code") {
                            Map rates = getExchangeRate(value.toString());
                            if ((Boolean)rates.get("success")) {
                                currencyWithITR.put("rateIdr", rates.get("rateIdr"));
                            }
                            else {
                                currencyWithITR.put("error", rates.get("error"));
                            }
                        }

                        currencyWithITR.put(key, value);
                    }
                }
            }

            Map containerMap = new HashMap();
            containerMap.put("full_name", countryMap.get("nativeName"));
            containerMap.put("population", countryMap.get("population"));
            containerMap.put("currencies", currencyWithITR);

            response = containerMap;
        }
        catch (Exception exc) {
            response.put("error", "Country details loading error occurred");
            System.out.println(exc.fillInStackTrace());
        }

        return response;
    }
}
