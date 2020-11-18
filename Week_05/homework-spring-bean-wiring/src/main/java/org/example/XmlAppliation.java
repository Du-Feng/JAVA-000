package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class XmlAppliation {
    public static void main(String[] args) {
        ApplicationContext xmlContent = new ClassPathXmlApplicationContext("xml-bean-context.xml");
        org.example.bean.wiring.xml.Person xmlBean = xmlContent.getBean("xmlBean", org.example.bean.wiring.xml.Person.class);
        log.info("XML Bean: {}", xmlBean.toString());

    }
}
