package com.example.transactiondemo;

import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.example.transactiondemo")
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/springdb");
        ds.setUsername("root");
        ds.setPassword("yourpassword");
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.example.transactiondemo");
        return factory;
    }
}
