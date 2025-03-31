package com.learn.tobyspring.chapter1.user.dao;

import com.learn.tobyspring.chapter1.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // JavaConfig 방식을 사용
        // ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);

        // Xml Bean 등록 방식
        ApplicationContext ac = new GenericXmlApplicationContext("/chapter1/applicationContext.xml");

        // 루트 패키지를 기점을 찾고 싶을 경우. (Java 클래스와 동일한 패키지 안에서 검색 - hint)
        // ApplicationContext ac = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao userDao = ac.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("chfotm");
        user.setName("김동현");
        user.setPassword("password1!");
        userDao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = userDao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());
        System.out.println(user2.getId() + "조회 성공");
    }
}
