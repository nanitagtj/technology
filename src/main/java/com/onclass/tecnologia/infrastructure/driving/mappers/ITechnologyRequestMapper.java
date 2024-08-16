package com.onclass.tecnologia.infrastructure.driving.mappers;

import com.onclass.tecnologia.domain.model.Technology;
import com.onclass.tecnologia.infrastructure.driving.dto.TechnologyRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
            unmappedSourcePolicy = ReportingPolicy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITechnologyRequestMapper {

    TechnologyRequestDto toDto(Technology technology);
    @Mapping(target = "id", ignore = true)
    Technology toDomain(TechnologyRequestDto technologyRequestDto);
}
