package com.dmitryshvalev.filmorate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FilmorateApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmorateApplication.class, args);
    }
}
