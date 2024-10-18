package com.assignment.live.weather.constants;

public class AppConstants {
   final public static String API_KEY = "48bf99058133a6af64d7d5a3eec477b1";
   final public static String WEATHER_DATA_BY_IP_URL = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={key}";
   final public static String LOCATION_API_URL = "https://ipinfo.io/{ip}/json";
   final public static String WEATHER_DATA_BY_CITY_URL = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={API key}";
}
