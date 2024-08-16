package com.onclass.tecnologia.domain.spi;

import com.onclass.tecnologia.domain.model.Technology;
import reactor.core.publisher.Mono;

public interface ITechnologyPersistencePort {
    Mono<Technology> saveTechnology(Technology technology);
    Mono<Technology> findByName(String name);
}
