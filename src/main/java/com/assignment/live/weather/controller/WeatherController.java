package com.assignment.live.weather.controller;

import com.assignment.live.weather.dto.WeatherAveragesResponse;
import com.assignment.live.weather.dto.WeatherDetailsResponse;
import com.assignment.live.weather.services.WeatherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class WeatherController {

    final private WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping("/weather-details")
    public ResponseEntity<WeatherDetailsResponse>getWeatherDetailsByIp() throws Exception{
        String ip = "106.77.142.137";
//        String xForwardedFor = request.getHeader("X-Forwarded-For");
//        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
//            ip = xForwardedFor.split(",")[0];
//        } else {
//            ip = request.getRemoteAddr();
//        }
        WeatherDetailsResponse response = weatherService.getWeatherDetailsByIpAddress(ip);
        return new ResponseEntity<WeatherDetailsResponse>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/weather-details/{city}")
    public ResponseEntity<WeatherDetailsResponse>getWeatherDetailsByCity(@PathVariable("city")String city) throws Exception{
        WeatherDetailsResponse response = weatherService.getWeatherDetailsByCity(city);
        return new ResponseEntity<WeatherDetailsResponse>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("/average-weather-details/{date}")
    public ResponseEntity<WeatherAveragesResponse> getAverageWeatherDetailsForDate(
            @PathVariable("date") String date) throws ParseException {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);


            WeatherAveragesResponse weatherAveragesResponse = weatherService.getWeatherAveragesForSpecificDate("Delhi", parsedDate);


            return new ResponseEntity<WeatherAveragesResponse>(weatherAveragesResponse,HttpStatus.ACCEPTED);

    }

}
