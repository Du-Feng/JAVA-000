package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.proxy.OrderServiceProxy;
import org.example.service.OrderService;
import org.example.service.OrderServiceImpl;

@Slf4j
public class App
{
    public static void main( String[] args )
    {
        OrderService orderService = new OrderServiceImpl();
        OrderService proxy = new OrderServiceProxy(orderService).getProxyObject();
        proxy.placeOrder();
    }
}
