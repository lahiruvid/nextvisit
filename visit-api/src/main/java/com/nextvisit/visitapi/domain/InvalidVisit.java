package com.nextvisit.visitapi.domain;

public class InvalidVisit extends RuntimeException {

    public InvalidVisit(String message) {
        super(message);
    }
}
