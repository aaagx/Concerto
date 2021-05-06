package com.example.concerto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"com.example.concerto.dao"})
@SpringBootApplication
@EnableTransactionManagement
public class ConcertoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConcertoApplication.class, args);
    }

}
