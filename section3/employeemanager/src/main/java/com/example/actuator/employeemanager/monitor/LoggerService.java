package com.example.actuator.employeemanager.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class LoggerService implements HealthIndicator {

    private static final String LOG_SERVICE = "loggerservice";

    @Override
    public Health health() {

        return isLoggerHealth()?Health.up().withDetail("LOG_SERVICE","Service is running").build()
                : Health.down().withDetail("LOG_SERVICE","Service is not available").build();
    }

    private boolean isLoggerHealth(){
        // logic
        return true;
    }
}
