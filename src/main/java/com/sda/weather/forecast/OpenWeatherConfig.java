package com.sda.weather.forecast;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "com.sda.weather.openweathermap-api")
public class OpenWeatherConfig {

    private String appid;
}
