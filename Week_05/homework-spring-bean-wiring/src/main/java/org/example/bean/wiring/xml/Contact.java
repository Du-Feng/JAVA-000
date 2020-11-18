package org.example.bean.wiring.xml;

import lombok.ToString;

@ToString
public class Contact {
    private String email;
    private String phone;

    public Contact(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }
}
