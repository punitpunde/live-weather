package com.assignment.live.weather.services;

import com.assignment.live.weather.constants.AppConstants;
import com.assignment.live.weather.dto.WeatherAveragesResponse;
import com.assignment.live.weather.dto.WeatherDetailsResponse;
import com.assignment.live.weather.repository.WeatherDetailsRepository;
import com.assignment.live.weather.utill.Location;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;


@Service
public class WetherServiceImpl implements  WeatherService {

    private final RestTemplate restTemplate;
    private final GeoLocationService geoLocationService;
    private final ObjectMapper objectMapper;
    private final WeatherDetailsRepository weatherDetailsRepository;

    public WetherServiceImpl(RestTemplate restTemplate, GeoLocationService geoLocationService, ObjectMapper objectMapper, WeatherDetailsRepository weatherDetailsRepository){
        this.restTemplate = restTemplate;
        this.geoLocationService = geoLocationService;
        this.objectMapper = objectMapper;
        this.weatherDetailsRepository = weatherDetailsRepository;
    }

    public WeatherDetailsResponse getWeatherDetailsByIpAddress(String ip) throws Exception {

        Location location = geoLocationService.getLocationByIp(ip);


        String response = restTemplate.getForObject(AppConstants.WEATHER_DATA_BY_IP_URL, String.class,
                location.getLatitude(), location.getLongitude(), AppConstants.API_KEY);


        JsonNode jsonResponse = objectMapper.readTree(response);


        return WeatherDetailsResponse.builder()
                .longitude(location.getLongitude())
                .latitude(location.getLatitude())
                .weatherMain(jsonResponse.path("weather").get(0).path("main").asText())
                .weatherDescription(jsonResponse.path("weather").get(0).path("description").asText())
                .icon(jsonResponse.path("weather").get(0).path("icon").asText())
                .temperature(jsonResponse.path("main").path("temp").asDouble())
                .feelsLike(jsonResponse.path("main").path("feels_like").asDouble())
                .humidity(jsonResponse.path("main").path("humidity").asInt())
                .windSpeed(jsonResponse.path("wind").path("speed").asDouble())
                .cityName(jsonResponse.path("name").asText())
                .build();
    }

    public WeatherDetailsResponse getWeatherDetailsByCity(String city) throws Exception {

        String response = restTemplate.getForObject(AppConstants.WEATHER_DATA_BY_CITY_URL, String.class,
                city, AppConstants.API_KEY);


        JsonNode jsonResponse = objectMapper.readTree(response);


        return WeatherDetailsResponse.builder()
                .weatherMain(jsonResponse.path("weather").get(0).path("main").asText())
                .weatherDescription(jsonResponse.path("weather").get(0).path("description").asText())
                .icon(jsonResponse.path("weather").get(0).path("icon").asText())
                .temperature(jsonResponse.path("main").path("temp").asDouble())
                .feelsLike(jsonResponse.path("main").path("feels_like").asDouble())
                .humidity(jsonResponse.path("main").path("humidity").asInt())
                .windSpeed(jsonResponse.path("wind").path("speed").asDouble())
                .cityName(jsonResponse.path("name").asText())
                .date(new Date())
                .build();
    }

    public WeatherAveragesResponse getWeatherAveragesForSpecificDate(String cityName, Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startDate = calendar.getTime();


        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endDate = calendar.getTime();

        WeatherAveragesResponse weatherAveragesResponse = weatherDetailsRepository.findWeatherAveragesForDay(cityName, startDate, endDate);
        System.out.println(weatherAveragesResponse);
        return weatherAveragesResponse;
    }
}
