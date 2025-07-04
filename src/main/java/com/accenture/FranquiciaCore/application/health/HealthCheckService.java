package com.accenture.FranquiciaCore.application.health;

import com.accenture.FranquiciaCore.domain.health.HealthCheck;
import com.accenture.FranquiciaCore.domain.health.HealthReport;
import com.accenture.FranquiciaCore.domain.health.HealthStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HealthCheckService {

    private final DatabaseHealthChecker databaseHealthChecker;
    private final ApplicationHealthChecker applicationHealthChecker;

    public Mono<HealthReport> performHealthCheck() {
        log.info("Starting health check...");
        
        return Mono.zip(
                applicationHealthChecker.checkApplicationHealth(),
                databaseHealthChecker.checkDatabaseHealth()
        ).map(tuple -> {
            HealthCheck appCheck = tuple.getT1();
            HealthCheck dbCheck = tuple.getT2();
            
            List<HealthCheck> checks = List.of(appCheck, dbCheck);
            HealthStatus overallStatus = determineOverallStatus(checks);
            Long totalResponseTime = checks.stream()
                    .mapToLong(check -> check.getResponseTime() != null ? check.getResponseTime() : 0)
                    .sum();
            
            return HealthReport.builder()
                    .applicationName("FranquiciaCore")
                    .overallStatus(overallStatus)
                    .timestamp(LocalDateTime.now())
                    .checks(checks)
                    .totalResponseTime(totalResponseTime)
                    .build();
        });
    }

    private HealthStatus determineOverallStatus(List<HealthCheck> checks) {
        boolean hasDown = checks.stream()
                .anyMatch(check -> check.getStatus() == HealthStatus.DOWN);
        
        if (hasDown) {
            return HealthStatus.DOWN;
        }
        
        boolean hasUnknown = checks.stream()
                .anyMatch(check -> check.getStatus() == HealthStatus.UNKNOWN);
        
        if (hasUnknown) {
            return HealthStatus.UNKNOWN;
        }
        
        return HealthStatus.UP;
    }
} 
