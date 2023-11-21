package com.scuec.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月14日 16:56
 * 项目名称:  CMS_my
 * 文件名称:  BaseDao
 * 文件描述:  @Description: 通用的一般jdbc方法
 */
public class BaseDao {
    public int executeUpdate(String sql, Object ...params) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i-1]);
        }
        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();
        //是否回收连接,需要考虑是不是事务!
        if (connection.getAutoCommit()) {
            //没有开启事务
            //没有开启事务 正常回收连接啦!
            JDBCUtils.freeConnection();
        }
        //connection.setAutoCommit(false); //开启事务了 不要管连接即可! 业务层处理!

        return rows;
    }

    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object ...params) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        if (params != null && params.length != 0) {
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i - 1]);
            }
        }
        preparedStatement.executeQuery(sql);

        ResultSetMetaData metaData = preparedStatement.getMetaData();
        int columnCount = metaData.getColumnCount();

        
    }

}
