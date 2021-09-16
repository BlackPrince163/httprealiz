package com.kpfu.itis.HW1;

import java.util.Map;

public class MyUtils {
    public static String makeUrlWithParams(String url, Map<String, String> params){
        int counter = 0;
        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append("?");

        for(String key : params.keySet()){
            if(counter >= 1){
                urlBuilder.append("&");
            }

            urlBuilder.append(key).append("=").append(params.get(key));
            counter++;
        }

        return urlBuilder.toString();
    }

    public static String makeJson(Map<String, String> params){
        int counter = 0;
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("{");

        for(String key : params.keySet()){
            if(counter >= 1){
                urlBuilder.append(",");
            }

            urlBuilder.append(key).append(":").append(params.get(key));
            counter++;
        }

        urlBuilder.append("}");
        return urlBuilder.toString();
    }
}
