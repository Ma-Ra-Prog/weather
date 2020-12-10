package com.sda.weather.exceptions;

public class WindMapperException extends RuntimeException {
    public WindMapperException(String message) {
        super("INTERNAL SERVER ERROR: " + message);
    }
}
