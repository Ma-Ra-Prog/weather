package com.sda.weather.exceptions;

public class NoCountryOrCityExcepton extends RuntimeException {
    public NoCountryOrCityExcepton(String message) {
        super("Lack of information: " + message);
    }
}
