package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        org.example.bean.wiring.annotation.Person annotationPerson = context.getBean(org.example.bean.wiring.annotation.Person.class);
        log.info("Annotation Bean: {}", annotationPerson.toString());

        org.example.bean.wiring.xml.Person xmlBean = context.getBean("xmlBean", org.example.bean.wiring.xml.Person.class);
        log.info("XML Bean: {}", xmlBean.toString());
    }
}
