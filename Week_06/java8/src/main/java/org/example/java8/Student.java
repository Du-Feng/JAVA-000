package org.example.java8;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class Student implements Serializable, ApplicationContextAware {
    private ApplicationContext applicationContext;

    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void init() {
        System.out.println("Hello ....................");
        log.debug("Initialize student");
    }

    public Student create() {
        this.applicationContext.getBeanDefinitionNames();
        Student student = new Student(100, "Feng Du");
        return student;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
