package org.example.bean.wiring.autoscan;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Data
@Component
public class PrimarySchool implements ISchool {
    @Resource(name = "klass")
    private Klass klass;

    @Resource(name = "student1")
    private Student student;

    @Override
    public void print() {
        log.info(this.toString());
    }
}
