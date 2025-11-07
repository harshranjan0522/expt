package com.example.studentmgmt.service;

import com.example.studentmgmt.dao.StudentDAO;
import com.example.studentmgmt.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeeService {

    @Autowired
    private StudentDAO dao;

    @Transactional
    public void payFee(int studentId, double amount) {
        Student s = dao.getStudent(studentId);
        if (s != null && s.getBalance() >= amount) {
            s.setBalance(s.getBalance() - amount);
            dao.updateStudent(s);
            System.out.println("Fee payment successful!");
        } else {
            throw new RuntimeException("Insufficient balance or student not found!");
        }
    }

    @Transactional
    public void refundFee(int studentId, double amount) {
        Student s = dao.getStudent(studentId);
        if (s != null) {
            s.setBalance(s.getBalance() + amount);
            dao.updateStudent(s);
            System.out.println("Refund processed successfully!");
        } else {
            throw new RuntimeException("Student not found!");
        }
    }
}
