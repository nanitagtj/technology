package com.onclass.tecnologia.infrastructure.driven.mappers;

import com.onclass.tecnologia.domain.model.Technology;
import com.onclass.tecnologia.infrastructure.driven.entity.TechnologyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TechnologyEntityMapper {
    Technology toModel(TechnologyEntity entity);
    TechnologyEntity toEntity(Technology technology);
}
