package com.sda.weather.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastOpenWeatherResponse {

    private List<SingleForecast> list;

    @Data
    public static class SingleForecast {
        @JsonProperty("dt_txt")
        private String date;
        private Wind wind;
        @JsonProperty("main")
        private Weather weather;
    }

    @Data
    public static class Wind {
        private int speed;
        @JsonProperty("deg")
        private float degree;
    }

    @Data
    public static class Weather {
        @JsonProperty("temp")
        private float temperature;
        private int pressure;
        private int humidity;
    }
}
