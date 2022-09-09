# Spring boot actuator

## Section 2: Spring boot actuator

### What is spring boot actuator ?

> it's a tool to manage and monitor spring boot applications . <br>
> Each endpoint can be exposed over http and jmx

### Endpoints location

> All HTTP endpoints are availabele at /actuator

### Documentation

> https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html

## Section 3: Spring boot actuator endpoints

set up a spring boot application and add required dependancies

run the application and go to http://localhost:8080/actuator it will show few items

```json
{
  "_links": {
    "self": { "href": "http://localhost:8080/actuator", "templated": false },
    "health": {
      "href": "http://localhost:8080/actuator/health",
      "templated": false
    },
    "health-path": {
      "href": "http://localhost:8080/actuator/health/{*path}",
      "templated": true
    }
  }
}
```

now go to the http://localhost:8080/actuator/health

```json
{ "status": "UP" }
```

### Showing all actuator endpoints

in the applicatin.properties file

> management.endpoints.web.exposure.include=\*

> management.endpoint.health.show-details=always

now the actuator will display all the endpoints

### Adding application information

Application information is under "actuator/info"

To customise this you can add the below in properties file

```
management.info.env.enabled=true
info.app-name = Employee manager application
info.app-description = This is an application to manage employees
info.app-version = 1.0

```

### Override actuator base path

> management.endpoints.web.base-path=/admin

### Custom health actuator

if you want to add more values to the health then implement the HealthIndicator to the service and override the values as per you requirement .

```java
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

```

### Creating custom endpoint

if you want to create your own actuator endpoint

```java
package com.example.actuator.employeemanager.monitor;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "custom")
@Component
public class CustomActuatorEndpoint {

    @ReadOperation
    public Object customObject() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        return map;
    }
}


```

and the json will look like this for "http://localhost:8080/actuator/custom"

```json
{ "key": "value" }
```
