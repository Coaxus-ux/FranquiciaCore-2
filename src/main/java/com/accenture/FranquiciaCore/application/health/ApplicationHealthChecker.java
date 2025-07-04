package com.accenture.FranquiciaCore.application.health;

import com.accenture.FranquiciaCore.domain.health.HealthCheck;
import reactor.core.publisher.Mono;

public interface ApplicationHealthChecker {
    Mono<HealthCheck> checkApplicationHealth();
} 
