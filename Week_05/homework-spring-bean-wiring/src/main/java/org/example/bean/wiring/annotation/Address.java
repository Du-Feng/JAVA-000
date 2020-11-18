package org.example.bean.wiring.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Address {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = "two address";
    }

    public void wire() {
        log.info("Wired Spring Bean: Address");
    }
}
