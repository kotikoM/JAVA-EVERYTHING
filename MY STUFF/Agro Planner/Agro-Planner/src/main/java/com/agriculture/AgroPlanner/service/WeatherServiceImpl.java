package com.agriculture.AgroPlanner.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${api.weather.key}")
    private String APIKey;
    private static final String weatherAPIRequestFormat = "http://api.weatherapi.com/v1/forecast.json?key=" +
            "%s" +
            "&q=%f,%f&days=3&hour=15";
    private static final String GET = "GET";
    private static final String HTTP_FAIL_MESSAGE = "HTTP Request Failed with response code: ";


    public ResponseEntity<String> fetchWeatherData(Double lat, Double lon) {
        String jsonResponse = makeApiRequest(lat, lon);
        return ResponseEntity.ok(jsonResponse);
    }

    private String makeApiRequest(Double lat, Double lon) {
        try {
            String request = String.format(weatherAPIRequestFormat, APIKey, lat, lon);
            URL url = new URL(request);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(GET);

            return getResponse(con);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getResponse(HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            throw new RuntimeException(HTTP_FAIL_MESSAGE + responseCode);
        }
    }
}
