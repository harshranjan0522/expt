package com.example.transactiondemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        BankService service = context.getBean(BankService.class);
        service.transferMoney(1, 2, 5000);

        context.close();
    }
}
