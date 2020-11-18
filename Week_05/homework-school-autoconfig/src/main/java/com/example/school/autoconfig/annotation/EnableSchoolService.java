package com.example.school.autoconfig.annotation;

import com.example.school.autoconfig.config.SchoolConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SchoolConfiguration.class)
public @interface EnableSchoolService {
}
