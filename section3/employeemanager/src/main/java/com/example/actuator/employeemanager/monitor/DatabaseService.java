package com.example.actuator.employeemanager.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService implements HealthIndicator {

    private static final String DB_SERVICE = "databaseservice";

    @Override
    public Health health() {

        return isdatabseHealth()?Health.up().withDetail("DB_SERVICE","Service is runnung").build()
        : Health.down().withDetail("DB_SERVICE","Service is not available").build();
    }

    private boolean isdatabseHealth(){
        // logic
        return true;
    }
}
