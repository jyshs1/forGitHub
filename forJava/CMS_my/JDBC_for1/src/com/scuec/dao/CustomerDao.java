package com.scuec.dao;

import com.scuec.com.CustomerService;
import com.scuec.javabean.Customer;
import com.scuec.utils.BaseDao;

import java.sql.SQLException;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月14日 16:52
 * 项目名称:  CMS_my
 * 文件名称:  CustomerDao
 * 文件描述:  @Description: dao方法
 */
public class CustomerDao extends BaseDao {
    public int addCustomer(Customer customer) throws SQLException {
        String sql = "insert into t_customer(id, NAME, gender, age, salary, phone)" +
                " values (?, ?, ?, ?, ?);";
        int rows = executeUpdate(sql, customer.getId(),
                customer.getName(), customer.getAge(), customer.getSalary(), customer.getPhone());
        return rows;
    }

    public Customer queryByID(int id) {
        String sql = "select * from t_customer" +
                " where  (?, ?, ?, ?, ?);";
    }
}
