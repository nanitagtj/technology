package com.onclass.tecnologia.infrastructure.driving.handlers;

import com.onclass.tecnologia.domain.model.Technology;
import com.onclass.tecnologia.infrastructure.driving.dto.TechnologyRequestDto;
import com.onclass.tecnologia.infrastructure.driving.dto.TechnologyResponseDto;
import reactor.core.publisher.Mono;

public interface ITechnologyHandler {

    Mono<TechnologyResponseDto> createTechnology(TechnologyRequestDto technologyRequestDto);
}
