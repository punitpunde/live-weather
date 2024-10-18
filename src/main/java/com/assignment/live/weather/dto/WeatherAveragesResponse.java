package com.assignment.live.weather.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WeatherAveragesResponse {
    private double averageTemperature;
    private double averageFeelsLike;
    private double averageHumidity;
    private double averageWindSpeed;
}
