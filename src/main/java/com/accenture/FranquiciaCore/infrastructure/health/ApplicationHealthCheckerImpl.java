package com.accenture.FranquiciaCore.infrastructure.health;

import com.accenture.FranquiciaCore.application.health.ApplicationHealthChecker;
import com.accenture.FranquiciaCore.domain.health.HealthCheck;
import com.accenture.FranquiciaCore.domain.health.HealthStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@Slf4j
public class ApplicationHealthCheckerImpl implements ApplicationHealthChecker {

    @Override
    public Mono<HealthCheck> checkApplicationHealth() {
        long startTime = System.currentTimeMillis();
        
        return Mono.fromCallable(() -> {
            try {
                long responseTime = System.currentTimeMillis() - startTime;
                
                return HealthCheck.builder()
                        .component("Application")
                        .status(HealthStatus.UP)
                        .message("Application is running normally")
                        .timestamp(LocalDateTime.now())
                        .responseTime(responseTime)
                        .build();
                        
            } catch (Exception e) {
                log.error("Error checking application health", e);
                long responseTime = System.currentTimeMillis() - startTime;
                
                return HealthCheck.builder()
                        .component("Application")
                        .status(HealthStatus.DOWN)
                        .message("Application health check failed: " + e.getMessage())
                        .timestamp(LocalDateTime.now())
                        .responseTime(responseTime)
                        .build();
            }
        });
    }
} 
