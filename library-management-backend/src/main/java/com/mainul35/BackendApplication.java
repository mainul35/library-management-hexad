package com.mainul35;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run (BackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(UUID.randomUUID().toString());
    }
}
