package com.learn.tobyspring.chapter2.user.dao;

import com.learn.tobyspring.chapter2.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest {

    @Test
    @DisplayName("회원 등록, 검색 테스트")
    public void addAndGet() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new GenericXmlApplicationContext("chapter2/applicationContext.xml");

        UserDao userDao = context.getBean("userDao", UserDao.class);

        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        User user1 = new User("chfotm", "김동현", "password1!");
        User user2 = new User("gyumee", "박상철", "springno1");

        userDao.add(user1);
        userDao.add(user2);
        assertThat(userDao.getCount()).isEqualTo(2);

        User userGet1 = userDao.get(user1.getId());
        assertThat(userGet1.getName()).isEqualTo(user1.getName());
        assertThat(userGet1.getPassword()).isEqualTo(user1.getPassword());

        User userGet2 = userDao.get(user2.getId());
        assertThat(userGet2.getName()).isEqualTo(user2.getName());
        assertThat(userGet2.getPassword()).isEqualTo(user2.getPassword());
    }

    @Test
    @DisplayName("회원 등록 및 집계 Test")
    public void count() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("chapter2/applicationContext.xml");

        UserDao userDao = context.getBean("userDao", UserDao.class);
        User user1 = new User("chfotm", "김동현", "password1!");
        User user2 = new User("gyumee", "박상철", "springno1");
        User user3 = new User("leegw700", "이길원", "springno2");
        User user4 = new User("bumjin", "박범진", "springno3");

        userDao.deleteAll();
        assertThat(userDao.getCount()).isEqualTo(0);

        userDao.add(user1);
        assertThat(userDao.getCount()).isEqualTo(1);

        userDao.add(user2);
        assertThat(userDao.getCount()).isEqualTo(2);

        userDao.add(user3);
        assertThat(userDao.getCount()).isEqualTo(3);

        userDao.add(user4);
        assertThat(userDao.getCount()).isEqualTo(4);

    }


}