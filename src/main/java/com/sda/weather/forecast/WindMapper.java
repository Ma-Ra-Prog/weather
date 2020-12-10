package com.sda.weather.forecast;

import com.sda.weather.exceptions.WindMapperException;
import org.springframework.stereotype.Component;

@Component
public class WindMapper {

    private final static String[] DIRECTIONS = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};

    String mapWindDegreeToWindDirection(float degree) {
        if (degree < 0 || degree > 360) {
            throw new WindMapperException("Wind direction can't be mapped");
        }
        double val = Math.floor((degree / 22.5) + 0.5);
        return DIRECTIONS[(int) (val % 16)];
    }
}
