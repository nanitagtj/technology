package com.onclass.tecnologia.infrastructure.driving.mappers;

import com.onclass.tecnologia.domain.model.Technology;
import com.onclass.tecnologia.infrastructure.driving.dto.TechnologyResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
            unmappedTargetPolicy = ReportingPolicy.IGNORE,
            unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITechnologyResponseMapper {
    TechnologyResponseDto toDto (Technology technology);
    Technology toDomain (TechnologyResponseDto technologyResponseDto);
}
