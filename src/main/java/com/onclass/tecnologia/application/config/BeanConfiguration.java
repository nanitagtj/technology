package com.onclass.tecnologia.application.config;

import com.onclass.tecnologia.domain.api.ITechnologyServicePort;
import com.onclass.tecnologia.domain.spi.ITechnologyPersistencePort;
import com.onclass.tecnologia.domain.usecase.TechnologyUseCase;
import com.onclass.tecnologia.domain.validations.Validations;
import com.onclass.tecnologia.infrastructure.driven.mappers.TechnologyEntityMapper;
import com.onclass.tecnologia.infrastructure.driven.r2dbc.TechnologyR2dbcAdapter;
import com.onclass.tecnologia.infrastructure.driven.repository.ITechnologyRepository;
import com.onclass.tecnologia.infrastructure.driving.handlers.ITechnologyHandler;
import com.onclass.tecnologia.infrastructure.driving.handlers.handlerimpl.TechnologyHandlerImpl;
import com.onclass.tecnologia.infrastructure.driving.mappers.ITechnologyRequestMapper;
import com.onclass.tecnologia.infrastructure.driving.mappers.ITechnologyResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITechnologyRepository technologyRepository;
    private final TechnologyEntityMapper technologyEntityMapper;
    private final ITechnologyRequestMapper technologyRequestMapper;
    private final ITechnologyResponseMapper technologyResponseMapper;

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyR2dbcAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public Validations validations() {
        return new Validations();
    }

    @Bean
    public ITechnologyServicePort technologyServicePort(ITechnologyPersistencePort technologyPersistencePort, Validations validations) {
        return new TechnologyUseCase(technologyPersistencePort, validations);
    }

    @Bean
    public ITechnologyHandler technologyHandler(ITechnologyServicePort technologyServicePort) {
        return new TechnologyHandlerImpl(technologyServicePort, technologyRequestMapper, technologyResponseMapper);
    }
}
