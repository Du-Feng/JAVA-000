package org.example.bean.wiring.autoscan;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class HighSchool implements ISchool {
    @Qualifier("klass")
    @Autowired
    private Klass klass;

    @Qualifier("student")
    @Autowired
    private Student student;

    @Override
    public void print() {
        log.info(this.toString());
    }
}
