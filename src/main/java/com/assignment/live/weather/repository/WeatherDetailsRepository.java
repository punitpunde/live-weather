package com.assignment.live.weather.repository;


import com.assignment.live.weather.dto.WeatherAveragesResponse;
import com.assignment.live.weather.entity.WeatherDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface WeatherDetailsRepository extends JpaRepository<WeatherDetailEntity,Long> {

    @Query("SELECT new com.assignment.live.weather.dto.WeatherAveragesResponse( " +
            "AVG(w.temperature), AVG(w.feelsLike), AVG(w.humidity), AVG(w.windSpeed)) " +
            "FROM WeatherDetailEntity w " +
            "WHERE w.cityName = :cityName AND w.date BETWEEN :startDate AND :endDate")
    WeatherAveragesResponse findWeatherAveragesForDay(@Param("cityName") String cityName,
                                                      @Param("startDate") Date startDate,
                                                      @Param("endDate") Date endDate);

}
