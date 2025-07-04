package com.accenture.FranquiciaCore.application.health;

import com.accenture.FranquiciaCore.domain.health.HealthCheck;
import reactor.core.publisher.Mono;

public interface DatabaseHealthChecker {
    Mono<HealthCheck> checkDatabaseHealth();
} 
