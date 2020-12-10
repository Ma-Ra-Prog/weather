package com.sda.weather.exceptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoCountryOrCityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void noCountryOrCityHandler(NoCountryOrCityException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void constraintViolationHandler(ConstraintViolationException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(InvalidCountryOrCityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void invalidCountryOrCityHandler(InvalidCountryOrCityException exception) {
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

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void badRequestHandler(BadRequestException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(JsonDataProcessingErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    void jsonDataProcessingErrorHandler(JsonDataProcessingErrorException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(ForecastFetchError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    void forecastFetchErrorHandler(ForecastFetchError exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(WindMapperException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    void forecastFetchErrorHandler(WindMapperException exception) {
        log.error(exception.getMessage());
    }

}
