package com.example.database.operation.jdbc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
@Configuration
public class ConnectionConfig {
    @Bean(name = "H2Connection")
    public Connection connection() {
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
            log.info("Connected to H2 database");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
