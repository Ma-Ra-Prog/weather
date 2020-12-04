package com.sda.weather.exceptions;

public class JsonDataProcessingErrorException extends RuntimeException {
    public JsonDataProcessingErrorException(String message) {
        super("DATA PROCESSING ERROR: " + message);
    }
}
