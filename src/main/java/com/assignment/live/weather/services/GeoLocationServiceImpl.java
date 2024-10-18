package com.assignment.live.weather.services;

import com.assignment.live.weather.constants.AppConstants;
import com.assignment.live.weather.utill.Location;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class GeoLocationServiceImpl implements GeoLocationService{

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;  // Jackson ObjectMapper

    public GeoLocationServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public Location getLocationByIp(String ip) throws IOException {

        String response = restTemplate.getForObject(AppConstants.LOCATION_API_URL, String.class, ip);
        System.out.println(ip);
        JsonNode jsonResponse = objectMapper.readTree(response);


        String loc = jsonResponse.get("loc").asText();
        String city = jsonResponse.get("city").asText();
        String country = jsonResponse.get("country").asText();


        String[] coordinates = loc.split(",");
        double latitude = Double.parseDouble(coordinates[0]);
        double longitude = Double.parseDouble(coordinates[1]);


        return new Location(latitude, longitude, city, country);
    }
}
