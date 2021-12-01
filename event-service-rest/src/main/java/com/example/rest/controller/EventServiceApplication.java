package com.example.rest.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.impl.repository"})
@ComponentScan(basePackages = {"com.example.impl", "com.example.rest"})
public class EventServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(EventServiceApplication.class, args);
  }
}
