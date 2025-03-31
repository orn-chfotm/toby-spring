package com.learn.tobyspring.user.dao;

import java.sql.*;

public class UserDao {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setId("chfotm");
        user.setName("김동현");
        user.setPassword("notmarried");

        userDao.add(user);

        System.out.println(user.getId());
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:23306/tobyspringdb", "root", "root"
        );

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
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:23306/tobyspringdb", "root", "root"
        );

        PreparedStatement ps = conn.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.println("columnCount = " + columnCount);
        for (int i = 0; i < columnCount; i++) {
            System.out.println("컬럼 :: " + i + metaData.getColumnName(i) + " :: " + metaData.getColumnTypeName(i));
        }

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        conn.close();

        return user;
    }
}
