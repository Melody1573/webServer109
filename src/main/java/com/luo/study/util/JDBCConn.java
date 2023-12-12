package com.luo.study.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 使用JAVA 自带的JDBC API来连接数据库
 */
public class JDBCConn {
    public static Connection getJdbcConn() throws ClassNotFoundException, SQLException {
        /**
         * 使用DriverManager来连接数据库
         */
        //加载驱动:MySQL、oracle、sqlserver、DM、人大金仓
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/dev?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url,username,password);
        return conn;
    }
}
