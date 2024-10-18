package com.assignment.live.weather.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@Builder
@Entity
public class WeatherDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
