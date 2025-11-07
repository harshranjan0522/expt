package com.example.transactiondemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {
    @Autowired
    private AccountDAO accountDAO;

    @Transactional
    public void transferMoney(int fromId, int toId, double amount) {
        Account from = accountDAO.getAccount(fromId);
        Account to = accountDAO.getAccount(toId);

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountDAO.updateAccount(from);
        accountDAO.updateAccount(to);
        System.out.println("Transaction Successful!");
    }
}
