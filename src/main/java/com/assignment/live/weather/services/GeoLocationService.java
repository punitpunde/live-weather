package com.assignment.live.weather.services;

import com.assignment.live.weather.utill.Location;

public interface GeoLocationService {
     Location getLocationByIp(String ip) throws Exception;
}
