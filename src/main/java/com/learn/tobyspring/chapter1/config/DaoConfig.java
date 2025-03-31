package com.learn.tobyspring.chapter1.config;

import com.learn.tobyspring.chapter1.user.dao.UserDao;
import com.mysql.cj.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoConfig {

    @Bean
    public UserDao userDao() {
        return new UserDao(dataSource());
    }

    /* CountingConnectionMaker
    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new DConnectionMacker();
    }
    */

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:23306/toby-spring-mysql");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }
}
