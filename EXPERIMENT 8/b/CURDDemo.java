package com.example.hibernatecrud;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class CRUDDemo {
    public static void main(String[] args) {
        // CREATE
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Student s1 = new Student("Harsh", "Java");
        session.persist(s1);
        tx.commit();
        session.close();
        System.out.println("Student added successfully!");

        // READ
        session = HibernateUtil.getSessionFactory().openSession();
        Student s = session.get(Student.class, s1.getId());
        System.out.println("Fetched Student: " + s.getName() + " - " + s.getCourse());
        session.close();

        // UPDATE
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        s.setCourse("Spring Boot");
        session.update(s);
        tx.commit();
        session.close();
        System.out.println("Student updated!");

        // DELETE
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
        session.delete(s);
        tx.commit();
        session.close();
        System.out.println("Student deleted!");
    }
}
