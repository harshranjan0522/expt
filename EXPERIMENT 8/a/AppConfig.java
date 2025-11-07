package com.example.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Course course() {
        return new Course();
    }

    @Bean
    public Student student() {
        return new Student(course());
    }
}
