package com.onclass.tecnologia.domain.exceptions;

import com.onclass.tecnologia.application.config.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class FieldsCannotBeNullException extends RuntimeException{
    private final ErrorResponse errorResponse;
    public FieldsCannotBeNullException(HttpStatus status, String message) {
        super(message);
        this.errorResponse = new ErrorResponse(status, message);
    }
}
