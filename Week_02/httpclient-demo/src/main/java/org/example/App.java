package org.example;

import org.example.model.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        try {
            OrderClient demo = new OrderClient();
            demo.getCoffeeMenu();

            demo.getCoffeeById(2);

            demo.getCoffeeByName("latte");

            Order order = Order.builder()
                    .customer("Feng Du")
                    .items(new ArrayList(Arrays.asList("latte", "mocha")))
                    .build();
            demo.createOrder("http://localhost:8080/order/", order);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
