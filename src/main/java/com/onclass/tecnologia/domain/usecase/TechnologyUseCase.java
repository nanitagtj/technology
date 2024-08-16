package com.onclass.tecnologia.domain.usecase;

import com.onclass.tecnologia.domain.api.ITechnologyServicePort;
import com.onclass.tecnologia.domain.model.Technology;
import com.onclass.tecnologia.domain.spi.ITechnologyPersistencePort;

import com.onclass.tecnologia.domain.validations.Validations;
import com.onclass.tecnologia.infrastructure.driving.exceptions.TechnologyNameAlreadyExistsException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.onclass.tecnologia.application.config.Constants.ERROR;


public class TechnologyUseCase implements ITechnologyServicePort {

    private final ITechnologyPersistencePort persistencePort;
    private final Validations validations;

    public TechnologyUseCase(ITechnologyPersistencePort persistencePort, Validations validations) {
        this.persistencePort = persistencePort;
        this.validations = validations;
    }

    public Flux<Technology> createTechnologies(Flux<Technology> technologies) {
        return technologies
                .flatMap(validations::validateTechnology)
                .flatMap(persistencePort::saveTechnology);
    }


}
