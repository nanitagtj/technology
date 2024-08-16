package com.onclass.tecnologia.domain.exceptions;

import org.springframework.http.HttpStatus;

public class TechnologyNameAlreadyExistsException extends RuntimeException {
    public TechnologyNameAlreadyExistsException() {
        super();
    }
}
