package com.sda.weather.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoCountryOrCityExcepton.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void noCountryOrCityHandler(NoCountryOrCityExcepton exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(BlankCountryOrCityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void blankCountryOrCityHandler(BlankCountryOrCityException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(IllegalParameterValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void illegalLatitudeOrLongitudeHandler(IllegalParameterValueException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void notFoundExceptionHandler(NotFoundException exception) {
        log.error(exception.getMessage());
    }
}
