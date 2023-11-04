package com.agriculture.AgroPlanner.controller;

import static com.agriculture.AgroPlanner.constants.Endpoints.WEATHER_ENDPOINT;

import com.agriculture.AgroPlanner.service.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(WEATHER_ENDPOINT)
@SuppressWarnings("unused")
public class WeatherController {
    @Autowired
    private WeatherServiceImpl weatherService;

    @GetMapping
    public ResponseEntity<String> retrieveWeatherInfo(@PathVariable Double lat, @PathVariable Double lon){
        return weatherService.fetchWeatherData(lat, lon);
    }
}
