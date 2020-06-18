package com.vicefix.crackwatch.http.handlers;

import com.vicefix.crackwatch.exceptions.BucketFullException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler(BucketFullException.class)
    public ResponseEntity<String> handleBucketFullException(BucketFullException e) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Rate-Limit-Retry-After-Milliseconds", Long.toString(e.getWaitForRefillTime()));

        return new ResponseEntity<>(e.getMessage(), httpHeaders, HttpStatus.TOO_MANY_REQUESTS);
    }

}