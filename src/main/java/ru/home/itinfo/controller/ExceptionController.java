package ru.home.itinfo.controller;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.home.itinfo.exception.NotFoundException;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleException(NotFoundException e) {
        return new ErrorMessage(e.getMessage());
    }
}
