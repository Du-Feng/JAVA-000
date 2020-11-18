package org.example.bean.wiring.autoscan;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Student implements Serializable {
    private static AtomicInteger counter = new AtomicInteger();
    private String name;
    private int id;

    public void init() {
        this.id = counter.incrementAndGet();
        this.name = "Feng Du " + id;
    }
}
