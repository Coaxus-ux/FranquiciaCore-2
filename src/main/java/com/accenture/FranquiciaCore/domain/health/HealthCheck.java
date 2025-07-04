package com.accenture.franquiciaCore.domain.health;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthCheck {
    private String component;
    private HealthStatus status;
    private String message;
    private LocalDateTime timestamp;
    private Long responseTime;
} 
