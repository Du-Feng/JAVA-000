package org.example.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Override
    public void placeOrder() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       log.info("Place order successfully!");
    }
}
