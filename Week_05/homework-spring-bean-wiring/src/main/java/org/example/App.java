package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class App {
    public static void main(String[] args) {
        ApplicationContext xmlContent = new ClassPathXmlApplicationContext("applicationContext.xml");

        org.example.bean.wiring.scan.Person scanPerson = xmlContent.getBean(org.example.bean.wiring.scan.Person.class);
        log.info("Annotation Bean: {}", scanPerson.toString());

        org.example.bean.wiring.xml.Person xmlBean = xmlContent.getBean("xmlBean", org.example.bean.wiring.xml.Person.class);
        log.info("XML Bean: {}", xmlBean.toString());

        ApplicationContext annotationContext = new AnnotationConfigApplicationContext(org.example.bean.wiring.annotation.PersonBeanConfiguration.class);
        org.example.bean.wiring.annotation.Person annotationPerson = annotationContext.getBean(org.example.bean.wiring.annotation.Person.class);
        log.info("Annotation Bean: {}", annotationPerson.toString());
    }
}
