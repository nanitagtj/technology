package com.onclass.tecnologia.domain.validations;

import com.onclass.tecnologia.domain.exceptions.DescriptionTooLongException;
import com.onclass.tecnologia.domain.exceptions.FieldsCannotBeNullException;
import com.onclass.tecnologia.domain.exceptions.NameTooLongException;
import com.onclass.tecnologia.domain.model.Technology;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import static com.onclass.tecnologia.application.config.Constants.*;

public class Validations {

    public Mono<Technology> validateTechnology(Technology technology) {
        return validateName(technology)
                .then(validateDescription(technology))
                .then(Mono.just(technology));
    }

    private Mono<Void> validateName(Technology technology) {
        if (technology.getName().length() > MAX_NAME_LENGTH) {
            return Mono.error(new NameTooLongException(
                    HttpStatus.BAD_REQUEST,
                    NAME_CHARACTERS_EXCEED));
        }
        if (technology.getName().isEmpty()) {
            return Mono.error(new FieldsCannotBeNullException(
                    HttpStatus.BAD_REQUEST,
                    NOT_NULL));
        }
        return Mono.empty();
    }

    private Mono<Void> validateDescription(Technology technology) {
        if (technology.getDescription().isEmpty()) {
            return Mono.error(new FieldsCannotBeNullException(
                    HttpStatus.BAD_REQUEST,
                    NOT_NULL));
        }
        if (technology.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            return Mono.error(new DescriptionTooLongException(
                    HttpStatus.BAD_REQUEST,
                    DESCRIPTION_CHARACTERS_EXCEED));
        }
        return Mono.empty();
    }
}
