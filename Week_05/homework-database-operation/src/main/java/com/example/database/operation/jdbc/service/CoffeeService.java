package com.example.database.operation.jdbc.service;

import com.example.database.operation.jdbc.entity.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class CoffeeService implements ICoffeeService {
    private String DROP_TABLE = "drop table if exists coffee CASCADE";
    private String CREATE_TABLE = "create table coffee (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), price DECIMAL(19,2), create_time timestamp, update_time timestamp)";
    private String INSERT_COFFEE = "insert into coffee (id, name, price, create_time, update_time)  values (null, ?, ?, ?, ?)";
    private String SELECT_COFFEE = "select name, price, create_time, update_time from coffee where id = ?";
    private String DELETE_COFFEE = "delete from coffee WHERE id = ?";
    private String UPDATE_COFFEE = "update coffee set name=?, price=?, update_time=? where id=?";

    private Connection connection;

    @Override
    public boolean init(Connection connection) {
        this.connection = connection;
        if (connection != null) {
            try {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(DROP_TABLE);
                stmt.executeUpdate(CREATE_TABLE);
                log.info("Created table");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void insert(Coffee coffee) {
        log.info("insert coffee ={}", coffee);
        try {
            int i = 1;
            PreparedStatement prepareStatement = connection.prepareStatement(INSERT_COFFEE);
            prepareStatement.setString(i++, coffee.getName());
            prepareStatement.setBigDecimal(i++, coffee.getPrice());
            prepareStatement.setDate(i++, Date.valueOf(coffee.getCreateTime()));
            prepareStatement.setDate(i++, Date.valueOf(coffee.getUpdateTime()));
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Coffee select(int id) {
        log.info("select coffee id ={}", id);
        try (PreparedStatement prepareStatement = connection.prepareStatement(SELECT_COFFEE);) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.first()) {
                String name = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                LocalDate createTime = resultSet.getDate("create_time").toLocalDate();
                LocalDate updateTime = resultSet.getDate("update_time").toLocalDate();
                return new Coffee(id, name, price, createTime, updateTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(DELETE_COFFEE);) {
            prepareStatement.setInt(1, id);
            prepareStatement.execute();
            log.info("Deleted coffee id: {}", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Coffee coffee) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(UPDATE_COFFEE);) {
            prepareStatement.setInt(4, coffee.getId());
            prepareStatement.setString(1, coffee.getName());
            prepareStatement.setBigDecimal(2, coffee.getPrice());
            prepareStatement.setDate(3, Date.valueOf(coffee.getUpdateTime()));
            prepareStatement.execute();
            log.info("Updated coffee: {}", coffee.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void batchInsert(List<Coffee> coffees) {
        try (PreparedStatement prepareStatement = connection.prepareStatement(INSERT_COFFEE)){
            connection.setAutoCommit(false);
            coffees.forEach(coffee->{
                try {
                    int i = 1;
                    prepareStatement.setString(i++, coffee.getName());
                    prepareStatement.setBigDecimal(i++, coffee.getPrice());
                    prepareStatement.setDate(i++, Date.valueOf(coffee.getCreateTime()));
                    prepareStatement.setDate(i++, Date.valueOf(coffee.getUpdateTime()));
                    prepareStatement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            prepareStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
