package com.accenture.franquiciaCore.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.franquiciaCore.application.health.HealthCheckService;
import com.accenture.franquiciaCore.domain.health.HealthReport;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
@Slf4j
public class HealthController {

    private final HealthCheckService healthCheckService;

    @GetMapping
    public Mono<ResponseEntity<HealthReport>> getHealthStatus() {
        log.info("Health check requested");
        
        return healthCheckService.performHealthCheck()
                .map(healthReport -> {
                    if (healthReport.getOverallStatus().name().equals("UP")) {
                        return ResponseEntity.ok(healthReport);
                    } else {
                        return ResponseEntity.status(503).body(healthReport);
                    }
                })
                .onErrorResume(error -> {
                    log.error("Error during health check", error);
                    return Mono.just(ResponseEntity.status(503).build());
                });
    }
} 
