package com.assignment.live.weather.services;
import com.assignment.live.weather.dto.WeatherDetailsResponse;
import com.assignment.live.weather.entity.WeatherDetailEntity;
import com.assignment.live.weather.repository.WeatherDetailsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DataUpdateServiceImpl implements DataUpdateService {


    private List<String> cities = List.of("Delhi","Bangalore","Mumbai");

    private final WeatherService weatherService;
    private final WeatherDetailsRepository weatherDetailsRepository;
    public DataUpdateServiceImpl(WeatherService weatherService, WeatherDetailsRepository weatherDetailsRepository) {
        this.weatherService = weatherService;
        this.weatherDetailsRepository = weatherDetailsRepository;
    }

    @Scheduled(fixedRate = 300000)
    public void fetchAndUpdateData() throws Exception {
        for (String city : cities) {
            WeatherDetailsResponse response = weatherService.getWeatherDetailsByCity(city);
            updateDatabase(response);
        }
    }
    private void updateDatabase(WeatherDetailsResponse response) {
        WeatherDetailEntity weatherDetailEntity = WeatherDetailEntity.builder()
                .longitude(response.getLongitude())
                .latitude(response.getLatitude())
                .weatherMain(response.getWeatherMain())
                .weatherDescription(response.getWeatherDescription())
                .icon(response.getIcon())
                .temperature(response.getTemperature())
                .feelsLike(response.getFeelsLike())
                .humidity(response.getHumidity())
                .windSpeed(response.getWindSpeed())
                .cityName(response.getCityName())
                .date(response.getDate())
                .build();
        System.out.println(response.getDate());
        weatherDetailsRepository.save(weatherDetailEntity);
    }

}
