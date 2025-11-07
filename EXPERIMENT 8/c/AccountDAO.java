package com.example.transactiondemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {
    @Autowired
    private SessionFactory factory;

    public Account getAccount(int id) {
        return factory.getCurrentSession().get(Account.class, id);
    }

    public void updateAccount(Account acc) {
        factory.getCurrentSession().update(acc);
    }
}
