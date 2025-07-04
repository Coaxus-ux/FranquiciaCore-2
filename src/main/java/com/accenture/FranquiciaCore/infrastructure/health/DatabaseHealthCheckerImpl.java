package com.accenture.franquiciaCore.infrastructure.health;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;

import com.accenture.franquiciaCore.application.health.DatabaseHealthChecker;
import com.accenture.franquiciaCore.domain.health.HealthCheck;
import com.accenture.franquiciaCore.domain.health.HealthStatus;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@Slf4j
public class DatabaseHealthCheckerImpl implements DatabaseHealthChecker {

    private final ReactiveMongoTemplate mongoTemplate;

    public DatabaseHealthCheckerImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<HealthCheck> checkDatabaseHealth() {
        long startTime = System.currentTimeMillis();
        
        return mongoTemplate.executeCommand("{ ping: 1 }")
                .map(result -> {
                    long responseTime = System.currentTimeMillis() - startTime;
                    
                    return HealthCheck.builder()
                            .component("MongoDB")
                            .status(HealthStatus.UP)
                            .message("Database connection is healthy")
                            .timestamp(LocalDateTime.now())
                            .responseTime(responseTime)
                            .build();
                })
                .onErrorResume(error -> {
                    log.error("Database health check failed", error);
                    long responseTime = System.currentTimeMillis() - startTime;
                    
                    return Mono.just(HealthCheck.builder()
                            .component("MongoDB")
                            .status(HealthStatus.DOWN)
                            .message("Database connection failed: " + error.getMessage())
                            .timestamp(LocalDateTime.now())
                            .responseTime(responseTime)
                            .build());
                });
    }
} 
