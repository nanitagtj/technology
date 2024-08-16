package com.onclass.tecnologia.domain.api;

import com.onclass.tecnologia.domain.model.Technology;
import reactor.core.publisher.Flux;
public interface ITechnologyServicePort {

    Flux<Technology> createTechnologies(Flux<Technology> technologies);
}
