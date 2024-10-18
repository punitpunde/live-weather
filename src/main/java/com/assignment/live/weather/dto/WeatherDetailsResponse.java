package com.assignment.live.weather.dto;


import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherDetailsResponse {
    private double longitude;
    private double latitude;
    private String weatherMain;
    private String weatherDescription;
    private String icon;
    private double temperature;
    private double feelsLike;
    private int humidity;
    private double windSpeed;
    private String cityName;
    private Date date;
}
