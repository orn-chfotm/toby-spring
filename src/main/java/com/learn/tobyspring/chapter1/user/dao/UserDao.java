package com.learn.tobyspring.chapter1.user.dao;

import com.learn.tobyspring.chapter1.user.domain.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    // Datasource 도입으로 connectionMaker 를 대체
    // private ConnectionMaker connectionMaker;
    private DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection conn = dataSource.getConnection();

        PreparedStatement ps = conn.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }

    /* Datasource 도입으로 connectionMaker 를 대체
    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }*/

    /* Bean 생성자 DI 도입으로 대체 - XML 주입 방식 사용되는 Setter */
    public void setDataSource(DataSource dataSource) { this.dataSource = dataSource; }
}
