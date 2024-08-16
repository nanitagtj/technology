package com.onclass.tecnologia.domain.api;

import com.onclass.tecnologia.domain.model.Technology;
import reactor.core.publisher.Mono;

public interface ITechnologyServicePort {

    Mono<Technology> createTechnology(Technology technology);

}
