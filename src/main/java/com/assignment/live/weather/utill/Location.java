package com.assignment.live.weather.utill;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private double latitude;
    private double longitude;
    private String city;
    private String country;
}
