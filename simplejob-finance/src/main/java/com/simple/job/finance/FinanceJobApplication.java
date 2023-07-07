package com.simple.job.finance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 添加日志
 */
@SpringBootApplication
@MapperScan("com.simple.job.finance.mapper")
public class FinanceJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinanceJobApplication.class, args);
    }
}
