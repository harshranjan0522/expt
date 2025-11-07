package com.example.studentmgmt.model;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    private String courseName;
    private String duration;

    public Course() {}

    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }

    // Getters and setters
    public int getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getDuration() { return duration; }
}
