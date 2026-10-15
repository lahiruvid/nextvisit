package com.nextvisit.visitapi.adapter.web;

import com.nextvisit.visitapi.domain.InvalidVisit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VisitExceptionHandler {

    @ExceptionHandler(InvalidVisit.class)
    public ResponseEntity<String> invalid(InvalidVisit error) {
        return ResponseEntity.badRequest().body(error.getMessage());
    }
}
