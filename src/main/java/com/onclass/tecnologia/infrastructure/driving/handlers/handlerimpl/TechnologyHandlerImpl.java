package com.onclass.tecnologia.infrastructure.driving.handlers.handlerimpl;

import com.onclass.tecnologia.domain.api.ITechnologyServicePort;
import com.onclass.tecnologia.domain.model.Technology;
import com.onclass.tecnologia.infrastructure.driving.dto.TechnologyRequestDto;
import com.onclass.tecnologia.infrastructure.driving.dto.TechnologyResponseDto;
import com.onclass.tecnologia.infrastructure.driving.handlers.ITechnologyHandler;
import com.onclass.tecnologia.infrastructure.driving.mappers.ITechnologyRequestMapper;
import com.onclass.tecnologia.infrastructure.driving.mappers.ITechnologyResponseMapper;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TechnologyHandlerImpl implements ITechnologyHandler {

    private final ITechnologyServicePort technologyService;
    private final ITechnologyRequestMapper technologyRequestMapper;
    private final ITechnologyResponseMapper technologyResponseMapper;
    @Override
    public Mono<TechnologyResponseDto> createTechnology(TechnologyRequestDto technologyRequestDto) {
        Technology technology = technologyRequestMapper.toDomain(technologyRequestDto);
        return technologyService.createTechnology(technology)
                .map(technologyResponseMapper::toDto);
    }

}
