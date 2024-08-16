package com.onclass.tecnologia.application.config;

import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorResponse {

    private final HttpStatus status;
    private final Object message;

    public ErrorResponse(HttpStatus status, Object message) {
        this.status = status;
        this.message = message;
    }

    public ResponseEntity<String> toResponseEntity() {

        String messageString = (message != null) ? message.toString() : "";
        return ResponseEntity
                .status(status)
                .body(messageString);
    }
}
