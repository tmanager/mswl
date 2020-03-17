package com.frank.mswl.web;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("com.frank.mswl.web.*.")
@SpringBootApplication(scanBasePackages = {"com.frank.mswl.web", "com.frank.mswl.core"})
public class MswlWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MswlWebApplication.class, args);
    }
}
