package com.accenture.franquiciaCore.domain.health;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthReport {
    private String applicationName;
    private HealthStatus overallStatus;
    private LocalDateTime timestamp;
    private List<HealthCheck> checks;
    private Long totalResponseTime;
} 
