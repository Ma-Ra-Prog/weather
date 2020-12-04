package com.sda.weather;

import com.sda.weather.weather.OpenWeatherConfig;
import com.sda.weather.weather.WeatherStackConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({WeatherStackConfig.class, OpenWeatherConfig.class})
public class WeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }

}
