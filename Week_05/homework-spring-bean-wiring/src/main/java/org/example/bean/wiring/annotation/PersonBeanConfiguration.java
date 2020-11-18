package org.example.bean.wiring.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="org.example.bean.wiring.annotation")
public class PersonBeanConfiguration {
    @Bean
    public Person getPerson() {
        return new Person("Feng", "Du");
    }
}
