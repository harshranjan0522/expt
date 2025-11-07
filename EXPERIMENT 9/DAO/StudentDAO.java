package com.example.studentmgmt.dao;

import com.example.studentmgmt.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentDAO {

    @Autowired
    private SessionFactory factory;

    // CREATE
    public void addStudent(Student s) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(s);
        tx.commit();
        session.close();
    }

    // READ
    public Student getStudent(int id) {
        Session session = factory.openSession();
        Student s = session.get(Student.class, id);
        session.close();
        return s;
    }

    // READ ALL
    public List<Student> getAllStudents() {
        Session session = factory.openSession();
        List<Student> list = session.createQuery("from Student", Student.class).list();
        session.close();
        return list;
    }

    // UPDATE
    public void updateStudent(Student s) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(s);
        tx.commit();
        session.close();
    }

    // DELETE
    public void deleteStudent(int id) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        Student s = session.get(Student.class, id);
        if (s != null) session.delete(s);
        tx.commit();
        session.close();
    }
}
