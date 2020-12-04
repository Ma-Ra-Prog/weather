package com.sda.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastFetchService forecastFetchService;

    @GetMapping("/weather")
    public ResponseEntity<ForecastDto> getForecast(@RequestParam String location) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(forecastFetchService.getForecast(location));
    }
}
