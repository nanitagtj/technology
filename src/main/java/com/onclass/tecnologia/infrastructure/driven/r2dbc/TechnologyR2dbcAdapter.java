package com.onclass.tecnologia.infrastructure.driven.r2dbc;

import com.onclass.tecnologia.application.config.ErrorResponse;
import com.onclass.tecnologia.domain.model.Technology;
import com.onclass.tecnologia.domain.spi.ITechnologyPersistencePort;
import com.onclass.tecnologia.infrastructure.driven.entity.TechnologyEntity;
import com.onclass.tecnologia.infrastructure.driven.mappers.TechnologyEntityMapper;
import com.onclass.tecnologia.infrastructure.driven.repository.ITechnologyRepository;
import com.onclass.tecnologia.infrastructure.driving.exceptions.TechnologyNameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import reactor.core.publisher.Mono;



@RequiredArgsConstructor
public class TechnologyR2dbcAdapter implements ITechnologyPersistencePort {

    private final ITechnologyRepository technologyRepository;
    private final TechnologyEntityMapper technologyEntityMapper;

    @Override
    public Mono<Technology> saveTechnology(Technology technology) {

        TechnologyEntity entity = technologyEntityMapper.toEntity(technology);
        return technologyRepository.save(entity)
                .map(technologyEntityMapper::toModel)
                .onErrorResume(DuplicateKeyException.class, ex -> Mono.error(new TechnologyNameAlreadyExistsException()));
    }


    @Override
    public Mono<Technology> findByName(String name) {
        return technologyRepository.findByName(name)
                .map(technologyEntityMapper::toModel);
    }

}
