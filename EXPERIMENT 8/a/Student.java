package com.example.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {
    private Course course;

    @Autowired
    public Student(Course course) {
        this.course = course;
    }

    public void showDetails() {
        System.out.println("Student is enrolled in: " + course.getCourseName());
    }
}
