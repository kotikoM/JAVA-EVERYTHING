package com.agriculture.AgroPlanner.service;

import org.springframework.http.ResponseEntity;

public interface WeatherService {
    ResponseEntity<String> fetchWeatherData(Double lat, Double lon);
}
