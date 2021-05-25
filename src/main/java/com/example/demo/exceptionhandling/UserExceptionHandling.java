package com.example.demo.exceptionhandling;

import com.example.demo.domain.Error;
import com.example.demo.exceptions.UserParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class UserExceptionHandling {

    private static final String BAD_PARAMETER = "bad input";

    @ExceptionHandler(UserParameterException.class)
    public ResponseEntity<Error> handleValidationException(UserParameterException ex) {
        var problem = new Error()
                .title(BAD_PARAMETER)
                .status(HttpStatus.BAD_REQUEST.value())
                .detail(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

}
