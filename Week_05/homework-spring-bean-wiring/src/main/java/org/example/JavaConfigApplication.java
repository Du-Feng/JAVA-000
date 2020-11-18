package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class JavaConfigApplication {
    public static void main(String[] args) {
        ApplicationContext annotationContext = new AnnotationConfigApplicationContext(org.example.bean.wiring.annotation.PersonBeanConfiguration.class);
        org.example.bean.wiring.annotation.Person annotationPerson = annotationContext.getBean(org.example.bean.wiring.annotation.Person.class);
        log.info("Annotation Bean: {}", annotationPerson.toString());
    }
}
