package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.proxy.OrderServiceProxy;
import org.example.service.OrderService;
import org.example.service.OrderServiceImpl;

import java.lang.reflect.Proxy;

@Slf4j
public class App {
    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        OrderServiceProxy orderServiceProxy = new OrderServiceProxy(orderService);
        OrderService proxy = orderServiceProxy.getProxyObject();
        proxy.placeOrder();
    }
}
