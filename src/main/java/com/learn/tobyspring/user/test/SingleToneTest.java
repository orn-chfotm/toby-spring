package com.learn.tobyspring.user.test;

import com.learn.tobyspring.config.DaoConfig;
import com.learn.tobyspring.user.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingleToneTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);

        /* Bean DI 경우 싱글톤 */
        /* 의존관계 주입 (Dependency Injection) <-> 의존관계 검색 (Dependency Lookup)*/
        /* 의존관계 주입은 능동적으로 Spring IOC 컨테이너가 타입 메서드명으로 Bean을 주입, 생성자 주입, 메서드 파라미터 주입 등이 의존관계 주입 */
        /* 의존관계 검색은 명시적으로 Bean을 할당, 아래의 getBean 방식이 의존관계 검색 유형 */
        System.out.println(context.getBean("userDao", UserDao.class));
        System.out.println(context.getBean("userDao", UserDao.class));
    }
}
