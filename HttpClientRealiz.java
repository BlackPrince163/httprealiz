package com.kpfu.itis.HW1;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;


public class HttpClientRealiz implements HttpClient {

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            URL myUrl = new URL(MyUtils.makeUrlWithParams(url, params));
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            StringBuilder result = new StringBuilder();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);

            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }

            System.out.println(connection.getResponseCode());

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                String input;

                while ((input = reader.readLine()) != null) {
                    result.append(input);
                }
            }

            connection.disconnect();
            return result.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            URL myUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            StringBuilder result = new StringBuilder();
            String json = MyUtils.makeJson(params);

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }

            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            System.out.println(connection.getResponseCode());

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String input;

                while ((input = reader.readLine()) != null) {
                    result.append(input.trim());
                }
            }

            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}