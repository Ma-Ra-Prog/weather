package com.sda.weather.weather;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties("com.sda.weather.openweathermap-api")
public class OpenWeatherConfig {

    private String apiKey;
    private String uri;
    private String units;
    private String lang;
}
