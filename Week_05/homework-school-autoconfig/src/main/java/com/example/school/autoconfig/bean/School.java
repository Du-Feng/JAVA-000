package com.example.school.autoconfig.bean;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@ToString
@Slf4j
public class School implements ISchool {
    Klass klass;
    Student student;

    @Override
    public void print() {
        log.info(this.toString());
    }

}
