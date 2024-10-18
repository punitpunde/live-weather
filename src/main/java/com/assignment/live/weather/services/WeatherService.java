package com.assignment.live.weather.services;

import com.assignment.live.weather.dto.WeatherAveragesResponse;
import com.assignment.live.weather.dto.WeatherDetailsResponse;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public interface WeatherService {
    public WeatherDetailsResponse getWeatherDetailsByIpAddress(String ip) throws Exception;
    public WeatherDetailsResponse getWeatherDetailsByCity(String city) throws Exception;
    public WeatherAveragesResponse getWeatherAveragesForSpecificDate(String cityName, Date date);
}
