package com.stock.sweet.sweetstockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SweetStockApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweetStockApiApplication.class, args);
    }

}
