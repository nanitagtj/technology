package com.onclass.tecnologia.application.config;

import com.onclass.tecnologia.domain.exceptions.DescriptionTooLongException;
import com.onclass.tecnologia.domain.exceptions.FieldsCannotBeNullException;
import com.onclass.tecnologia.domain.exceptions.NameTooLongException;
import com.onclass.tecnologia.domain.exceptions.TechnologyNameAlreadyExistsException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static com.onclass.tecnologia.application.config.Constants.*;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Mono<ResponseEntity<List<String>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError fieldError) {
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return Mono.just(new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(TechnologyNameAlreadyExistsException.class)
    public Mono<ResponseEntity<String>> handleTechnologyNameAlreadyExistsException(TechnologyNameAlreadyExistsException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(TECHNOLOGY_NAME_DUPLICATE));
    }

    @ExceptionHandler(NameTooLongException.class)
    public Mono<ResponseEntity<String>> handleNameTooLongException(NameTooLongException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(NAME_CHARACTERS_EXCEED));
    }

    @ExceptionHandler(DescriptionTooLongException.class)
    public Mono<ResponseEntity<String>> handleDescriptionTooLongException(DescriptionTooLongException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DESCRIPTION_CHARACTERS_EXCEED));
    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public Mono<ResponseEntity<String>> handleDataAccessResourceFailureException(DataAccessResourceFailureException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DB_ERROR));
    }

    @ExceptionHandler(FieldsCannotBeNullException.class)
    public Mono<ResponseEntity<String>> handleFieldsCannotBeNullException(FieldsCannotBeNullException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(NOT_NULL));
    }

}
