package com.example.complexcontroller.controller;

import com.example.complexcontroller.controller.request.NewOrderRequest;
import com.example.complexcontroller.model.Coffee;
import com.example.complexcontroller.model.CoffeeOrder;
import com.example.complexcontroller.service.CoffeeOrderService;
import com.example.complexcontroller.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeOrderService orderService;
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/{id}")
    private CoffeeOrder getOrder(@PathVariable("id") Long id) {
        return orderService.get(id);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new order: {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[]{});
        return orderService.createOrder(newOrder.getCustomer(), coffeeList);
    }
}
