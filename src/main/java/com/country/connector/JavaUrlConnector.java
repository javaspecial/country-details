package com.country.connector;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


public class JavaUrlConnector {
    private static Map<String, SSLSocketFactory> tls = new HashMap<String, SSLSocketFactory>();
    public static final String DATA_TYPE_XML = "xml";
    public static final String DATA_TYPE_JSON = "json";
    public static final String DATA_TYPE_URL_ENCODED = "url_encoded";

    public static Map callUrl(String requestURL, String method, String requestData, String dataType, Map headers) throws Exception {
        System.setProperty("sun.net.http.retryPost", "false" );
        URLConnection connection = null;
        String httpResponse = "", responseMessage = "", responseHeader = "", responseContentTypeFinal = "";
        Integer httpCode = 0;
        Boolean isTimeOut = false, unknownHost = false;
        Integer timeOutMilli = 300000;

        try {
            String contentType = null, accept = null;
            Integer contentLength = requestData.length();

            switch (dataType) {
                case DATA_TYPE_XML:
                    contentType = "text/xml; charset=utf-8";
                    accept = "text/xml";
                    break;
                case DATA_TYPE_JSON:
                    contentType = "application/json";
                    break;
                case DATA_TYPE_URL_ENCODED:
                    contentType = "application/x-www-form-urlencoded";
                    break;
            }
            if (requestURL.startsWith("https")) {
                connection = new URL(requestURL).openConnection();
            }
            else {
                connection = new URL(requestURL).openConnection();
            }

            connection.setRequestProperty("method", method);
            connection.setDoOutput(true);
            connection.setConnectTimeout(timeOutMilli);
            connection.setReadTimeout(timeOutMilli);
            if (method.equalsIgnoreCase("post") || method.equalsIgnoreCase("put") || method.equalsIgnoreCase("delete")) {
                connection.setDoInput(true);
                if (headers != null) {

                }
                if (accept != null && accept.length() > 0) {
                    connection.setRequestProperty("Accept", accept);
                }
                if (contentType != null) {
                    connection.setRequestProperty("Content-Type", contentType);
                }
                connection.setRequestProperty("Pragma", "no-cache");
                if (requestData.length() > 0) {
                    connection.setRequestProperty("Content-length", contentLength.toString());
                    OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                    writer.write(requestData);
                    writer.flush();
                    writer.close();
                }
            }
            else {
                connection.setRequestProperty("Pragma", "no-cache");
                if (headers != null) {

                }
            }
            if(connection != null) {
                InputStream stream = (InputStream) connection.getInputStream();
                String responseContentType = connection.getContentType();
                responseContentTypeFinal = responseContentType;

                if (responseContentType.contains(";")) {
                    responseContentType = responseContentType.substring(0, responseContentType.indexOf(";"));
                }
                if (responseContentType.contains("text/html")) {
                    responseContentType = "html";
                }
                else if (responseContentType.contains("image/")) {
                    responseContentType = "image";
                }
                else if (responseContentType.contains("/")) {
                    responseContentType =  "txt";
                    if (responseContentType.contains("+")) {
                        responseContentType = "txt";
                    }
                }

                httpResponse = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));

                try {
                    if (connection.getHeaderFields().size() != 0) {
                        StringBuilder headerBuilder = new StringBuilder();
                        Map<String, List<String>> headerFields = connection.getHeaderFields();
                        Set<String> headerFieldsSet = headerFields.keySet();
                        Iterator<String> hearerFieldsIter = headerFieldsSet.iterator();

                        while (hearerFieldsIter.hasNext()) {
                            String headerFieldKey = hearerFieldsIter.next();
                            List<String> headerFieldValue = headerFields.get(headerFieldKey);
                            StringBuilder sb = new StringBuilder();
                            for (String value : headerFieldValue) {
                                sb.append(value);
                                sb.append("");
                            }
                            headerBuilder.append(headerFieldKey + "=" + sb.toString() + ",\n");
                        }
                        responseHeader = headerBuilder.toString();
                    }
                }
                catch (Throwable ex) {
                    responseHeader = ex.getMessage();
                }
            }
        }
        catch (MalformedURLException ex) {
            responseMessage = ex.getMessage();
        }
        catch (UnknownHostException ex) {
            unknownHost = true;
            responseMessage = ex.getMessage();
        }
        catch (SocketTimeoutException ex) {
            isTimeOut = true;
            responseMessage = ex.getMessage();
        }
        catch (Throwable ex) {
            responseMessage = ex.getMessage();
        }
        finally {

        }


        Map response = new HashMap<String, String>();
        response.put("code", httpCode);
        response.put("response", httpResponse);
        response.put("message", responseMessage);
        response.put("isTimeOut", isTimeOut);
        response.put("unknownHost", unknownHost);
        response.put("responseHeader", responseHeader);
        response.put("responseContentType", responseContentTypeFinal);
        response.put("method", method);

        return response;
    }
}
