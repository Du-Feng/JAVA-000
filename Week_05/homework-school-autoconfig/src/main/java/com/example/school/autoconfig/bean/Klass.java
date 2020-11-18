package com.example.school.autoconfig.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class Klass {
    String klassName;
    List<Student> students;

    public void print() {
        log.info(this.toString());
    }

}
