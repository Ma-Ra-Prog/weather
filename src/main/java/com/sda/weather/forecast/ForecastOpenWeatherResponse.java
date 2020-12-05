package com.sda.weather.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastOpenWeatherResponse {

    private int cnt;
    private String cod;
    private CityResponse city;
    private List<SingleForecast> list;

    @Data
    public static class CityResponse {
        private String name;
    }

    @Data
    public static class SingleForecast {
        @JsonProperty("dt_txt")
        private String date;
    }
}
