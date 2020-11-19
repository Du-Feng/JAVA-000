package com.example.database.operation.jdbc.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
    private int id;
    private String name;
    private BigDecimal price;
    private LocalDate createTime;
    private LocalDate updateTime;
}