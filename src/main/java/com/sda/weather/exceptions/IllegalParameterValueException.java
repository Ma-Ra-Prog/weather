package com.sda.weather.exceptions;

public class IllegalParameterValueException extends IllegalArgumentException {
    public IllegalParameterValueException(String message) {
        super("Invalid: " + message);
    }
}
