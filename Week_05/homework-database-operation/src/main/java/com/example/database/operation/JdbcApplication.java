package com.example.database.operation;

import com.example.database.operation.jdbc.entity.Coffee;
import com.example.database.operation.jdbc.service.CoffeeService;
import com.example.database.operation.jdbc.service.ConnectionConfig;
import com.example.database.operation.jdbc.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackageClasses = JdbcApplication.class,
        exclude = DataSourceAutoConfiguration.class)
@Slf4j
public class JdbcApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConnectionConfig.class);
        Connection connection = (Connection) ctx.getBean("H2Connection");

        ICoffeeService service = new CoffeeService();
        if (service.init(connection)) {
            Coffee latte = Coffee.builder()
                    .name("Latte")
                    .price(new BigDecimal(14.99))
                    .createTime(LocalDate.now())
                    .updateTime(LocalDate.now())
                    .build();
            service.insert(latte);

            Coffee espresso = Coffee.builder()
                    .name("espresso")
                    .price(new BigDecimal(19.99))
                    .createTime(LocalDate.now())
                    .updateTime(LocalDate.now())
                    .build();
            Coffee mocha = Coffee.builder()
                    .name("mocha")
                    .price(new BigDecimal(9.99))
                    .createTime(LocalDate.now())
                    .updateTime(LocalDate.now())
                    .build();
            Coffee kombucha = Coffee.builder()
                    .name("kombucha")
                    .price(new BigDecimal(24.99))
                    .createTime(LocalDate.now())
                    .updateTime(LocalDate.now())
                    .build();
            List<Coffee> coffees = new ArrayList<>();
            coffees.add(espresso);
            coffees.add(mocha);
            coffees.add(kombucha);
            service.batchInsert(coffees);

            latte.setPrice(new BigDecimal(49.99));
            service.update(latte);

            Coffee coffee = service.select(3);
            if (coffee != null) {
                log.info(coffee.toString());
                service.delete(coffee.getId());
            }
        }
    }
}
