package com.scuec.com;

import com.scuec.dao.CustomerDao;
import com.scuec.javabean.Customer;

import java.sql.SQLException;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月14日 16:23
 * 项目名称:  CMS_my
 * 文件名称:  CustomerService
 * 文件描述:  @Description: service方法
 */
public class CustomerService {
    CustomerDao customerDao = new CustomerDao();
    public int addCustomer(Customer customer) throws SQLException {
        return customerDao.addCustomer(customer);
    }

    public Customer getCustomer(int id) {
        return customerDao.queryByID(id);
    }
}
