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

    public static String getExchangeRate(String code) {
        try {
            if (code != null) {
                String requestURL = "https://.com/v2/name/"+code+"?fullText=true";
                String method = "GET", data = "", dataType = JavaUrlConnector.DATA_TYPE_JSON;
                Map headers = new HashMap();

                Map responseMap = JavaUrlConnector.callUrl(requestURL, method, data, dataType, headers);
                ObjectMapper mapper = new ObjectMapper();
                Object jsonObject = new JSONArray(responseMap.get("response").toString()).get(0);
                HashMap<String, Object> countryMap = mapper.readValue(((JSONObject) jsonObject).toString(), HashMap.class);

                code = "5";
            }
        }
        catch (Exception e) {
            code = e.getMessage();
            System.out.println(e.getMessage());
        }
        return code;
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
                            currencyWithITR.put("rateIdr", getExchangeRate(value.toString()));
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
            response.put("error", exc.getMessage());
            System.out.println(exc.fillInStackTrace());
        }

        return response;
    }
}
