package com.example.school.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@ConditionalOnProperty(prefix = "spring.school", name = "enable", havingValue = "true", matchIfMissing = false)
@Import(SchoolConfiguration.class)
public class SchoolStarterConfiguration implements EnvironmentAware {
    private Environment environment;

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
