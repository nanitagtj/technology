package com.onclass.tecnologia.domain.exceptions;

import com.onclass.tecnologia.application.config.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class NameTooLongException extends RuntimeException {
    private final ErrorResponse errorResponse;
    public NameTooLongException(HttpStatus status, String message) {
        super(message);
        this.errorResponse = new ErrorResponse(status, message);
    }
}
