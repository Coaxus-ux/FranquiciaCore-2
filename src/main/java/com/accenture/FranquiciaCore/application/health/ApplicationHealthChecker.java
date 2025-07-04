package com.accenture.franquiciaCore.application.health;

import com.accenture.franquiciaCore.domain.health.HealthCheck;

import reactor.core.publisher.Mono;

public interface ApplicationHealthChecker {
    Mono<HealthCheck> checkApplicationHealth();
} 
