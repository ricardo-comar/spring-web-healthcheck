# spring-web-healthcheck

Simple PoC project, created using Spring Boot, with Maven 3, Spring Batch and [Actuator](http://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html#production-ready-endpoints). There's also two implementations of health check and metrics.

### Usage
- Download the repo
- Package with `mvn package`
- And run with `java -jar target/spring-web-healthcheck-0.0.1-SNAPSHOT.jar 
- Access endpoints "/metrics" and "/health" from localhost:8080
- A simple test is disabling your local network and calling again each endpoint, to see how it changed. And after establish the internet connection, call again to see returning to normal state.


### How it works
- An instance will be online in a few seconds. 
- A health check will be available, showing the avaliability of two external services (SOAP and REST). The SOAP service is critical, changing the health check state to "DOWN" if unavailable. And the REST service, if unavailable, will make the health check state to "OUT_OF_SERVICE".
- Both services are also monitored by the "/metrics" endpoint, showing last execution HTTP return code and response time 

### Under the hood
- The SOAP service access a [public forecast service](http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP), and the REST service access a [random quotes service](http://gturnquist-quoters.cfapps.io/api/random).
- The metrics services implements [PublicMetrics](http://docs.spring.io/autorepo/docs/spring-boot/1.2.5.RELEASE/api/org/springframework/boot/actuate/endpoint/PublicMetrics.html), providing response code and response time for each service.
- And the health check implements [HealthIndicator](http://docs.spring.io/autorepo/docs/spring-boot/1.2.5.RELEASE/api/org/springframework/boot/actuate/health/HealthIndicator.html) for each service, changing the application health status according to each service avaliability.
