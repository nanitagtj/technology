package com.onclass.tecnologia.infrastructure.driving.http.controller;

import com.onclass.tecnologia.infrastructure.driving.dto.TechnologyRequestDto;
import com.onclass.tecnologia.infrastructure.driving.dto.TechnologyResponseDto;
import com.onclass.tecnologia.infrastructure.driving.handlers.ITechnologyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/onclass")
@RequiredArgsConstructor
public class TechnologyController {

    private final ITechnologyHandler technologyHandler;
    @Operation(summary = "Add a new technologies",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Technology created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Technology already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/technologies")
    public Flux<TechnologyResponseDto> createTechnologies(@Valid @RequestBody Flux<TechnologyRequestDto> technologyRequestDtos) {
        return technologyHandler.createTechnologies(technologyRequestDtos);

    }

}
