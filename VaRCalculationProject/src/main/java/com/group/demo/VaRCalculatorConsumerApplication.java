package com.group.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.group")
public class VaRCalculatorConsumerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VaRCalculatorConsumerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    	
    }
}