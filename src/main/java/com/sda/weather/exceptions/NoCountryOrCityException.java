package com.sda.weather.exceptions;

public class NoCountryOrCityException extends RuntimeException {
    public NoCountryOrCityException(String message) {
        super("Lack of information: " + message);
    }
}
