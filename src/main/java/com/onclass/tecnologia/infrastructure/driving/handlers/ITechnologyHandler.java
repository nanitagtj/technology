package com.onclass.tecnologia.infrastructure.driving.handlers;

import com.onclass.tecnologia.infrastructure.driving.dto.TechnologyRequestDto;
import com.onclass.tecnologia.infrastructure.driving.dto.TechnologyResponseDto;
import reactor.core.publisher.Flux;

public interface ITechnologyHandler {

    Flux<TechnologyResponseDto> createTechnologies(Flux<TechnologyRequestDto> technologyRequestDtos);
}
