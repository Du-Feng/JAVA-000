package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.bean.wiring.autoscan.HighSchool;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Slf4j
@ImportResource("applicationContext.xml")
@Configuration
@ComponentScan(basePackages = {"org.example.bean.wiring.autoscan"})
public class AutoScanApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoScanApplication.class);
        HighSchool school = applicationContext.getBean(HighSchool.class);
        school.print();
    }
}
