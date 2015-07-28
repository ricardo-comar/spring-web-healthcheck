package br.comar.ricardo.stuff.healthcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableAutoConfiguration
@ImportResource("classpath*:spring-context.xml")
public class SpringWebHealthcheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebHealthcheckApplication.class, args);
    }
}
