package com.onclass.tecnologia.application.config;

import com.onclass.tecnologia.application.config.utils.ErrorResponse;
import io.micrometer.common.lang.NonNull;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Order(-2)
public class GlobalWebExceptionHandler implements WebExceptionHandler {

    @Override
    public @NonNull Mono<Void> handle(@NonNull ServerWebExchange exchange,@NonNull Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

        String errorMessage = ErrorResponse.createErrorMessage(ex);
        byte[] bytes = errorMessage.getBytes(StandardCharsets.UTF_8);
        response.getHeaders().setContentLength(bytes.length);

        return response.writeWith(Mono.just(response.bufferFactory().wrap(bytes)));
    }
}
