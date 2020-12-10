package com.sda.weather.exceptions;

public class ForecastFetchError extends RuntimeException {
    public ForecastFetchError(String message) {
        super("INTERNAL SERVER ERROR: " + message);
    }
}
