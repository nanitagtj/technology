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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/onclass")
@RequiredArgsConstructor
public class TechnologyController {

    private final ITechnologyHandler technologyHandler;
    @Operation(summary = "Add a new technology",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Technology created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Technology already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/technology")
    public Mono<ResponseEntity<TechnologyResponseDto>> createTechnology(@Valid @RequestBody TechnologyRequestDto technologyRequestDto) {
        return technologyHandler.createTechnology(technologyRequestDto)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response));
    }

}
