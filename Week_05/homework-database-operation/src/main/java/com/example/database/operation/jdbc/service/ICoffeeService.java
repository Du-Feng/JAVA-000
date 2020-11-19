package com.example.database.operation.jdbc.service;

import com.example.database.operation.jdbc.entity.Coffee;

import java.sql.Connection;
import java.util.List;

public interface ICoffeeService {
    boolean init(Connection connection);
    void insert(Coffee coffee);
    Coffee select(int id);
    void delete(int id);
    void update(Coffee coffee);
    void batchInsert(List<Coffee> coffees);
}
