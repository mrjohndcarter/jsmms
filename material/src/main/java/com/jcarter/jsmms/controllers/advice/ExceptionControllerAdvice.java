package com.jcarter.jsmms.controllers.advice;

import com.jcarter.jsmms.models.ErrorDetails;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorDetails> exceptionSingleResultNotFound() {
        ErrorDetails erd = new ErrorDetails();
        erd.setMessage("Material with specified ID not found.");
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(erd);
    }

}
