package com.scuec.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource dataSource = null;

    //线程
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        InputStream ips = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        System.out.println(ips);
        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() throws SQLException {
        //先查看线程本地 是否有
        Connection connection = tl.get();
        if (connection == null) {
            connection = dataSource.getConnection();
            tl.set(connection);
        }
        return connection;
    }

    public static void freeConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection != null) {
            tl.remove();
            connection.setAutoCommit(true);
            connection.close();
        }

    }
}
