package com.learn.tobyspring.chapter2.user.dao;

import com.learn.tobyspring.chapter2.user.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    // private ConnectionMaker connectionMaker;
    private DataSource dataSource;

    public UserDao() {
    }

    public void add(User user) throws SQLException {
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

    public User get(String id) throws SQLException {
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
    
    public void deleteAll() throws SQLException {
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("delete from users");

        ps.executeUpdate();
        
        ps.close();
        c.close();
    }
    
    public int getCount() throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
    }

    /* Bean 생성자 DI 도입으로 대체 - XML 주입 방식 사용되는 Setter */
    public void setDataSource(DataSource dataSource) { this.dataSource = dataSource; }
}
