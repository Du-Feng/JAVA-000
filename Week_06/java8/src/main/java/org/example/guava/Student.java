package org.example.guava;

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
public class Student implements Serializable {
    private int id;
    private String name;

    public void init() {
        System.out.println("Hello ....................");
        log.debug("Initialize student");
    }

    public Student create() {
        Student student = new Student(100, "Feng Du");
        return student;
    }
}
