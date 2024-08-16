package com.onclass.tecnologia.domain.usecase;

import com.onclass.tecnologia.domain.api.ITechnologyServicePort;
import com.onclass.tecnologia.domain.exceptions.DescriptionTooLongException;
import com.onclass.tecnologia.domain.exceptions.FieldsCannotBeNullException;
import com.onclass.tecnologia.domain.exceptions.NameTooLongException;
import com.onclass.tecnologia.domain.exceptions.TechnologyNameAlreadyExistsException;
import com.onclass.tecnologia.domain.model.Technology;
import com.onclass.tecnologia.domain.spi.ITechnologyPersistencePort;

import reactor.core.publisher.Mono;

public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort persistencePort;

    public TechnologyUseCase(ITechnologyPersistencePort persistencePort){
        this.persistencePort = persistencePort;
    }

    @Override
    public Mono<Technology> createTechnology(Technology technology) {

        if (technology.getName().length() > 50) {
            return Mono.error(new NameTooLongException());
        }

        if (technology.getName().isEmpty()  || technology.getDescription().isEmpty()) {
            return Mono.error(new FieldsCannotBeNullException());
        }

        if (technology.getDescription().length() > 90) {
            return Mono.error(new DescriptionTooLongException());
        }
        Mono<Boolean> exist = persistencePort.findByName(technology.getName()).hasElement();

        return exist.flatMap(existing -> {
            if (Boolean.TRUE.equals(existing)) {
                return Mono.error(new TechnologyNameAlreadyExistsException());
            } else {
                return persistencePort.saveTechnology(technology);
            }
        });
    }

}
