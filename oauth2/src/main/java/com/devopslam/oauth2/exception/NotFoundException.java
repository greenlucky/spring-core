package com.devopslam.oauth2.exception;

import lombok.Getter;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
