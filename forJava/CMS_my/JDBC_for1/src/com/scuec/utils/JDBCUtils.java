package com.scuec.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月14日 16:58
 * 项目名称:  CMS_my
 * 文件名称:  JDBCUtils
 * 文件描述:  @Description: JDBC连接方法
 */
public class JDBCUtils {
    private static DataSource dataSource = null; //连接池对象

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    static {
        Properties properties = new Properties();
        InputStream ips = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dataSource =   DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static Connection getConnection() throws SQLException {
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
            connection.setAutoCommit(true);
            connection.close();
            tl.remove();
        }
    }

}
