package com.scuec.javabean;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月14日 16:19
 * 项目名称:  CMS_my
 * 文件名称:  Customer
 * 文件描述:  @Description: 顾客类
 */
public class Customer {
    private int id;
    private String name;// : 姓名
    private String gender; // 性别
    private int age;// : 年龄
    private double salary;// : 工资
    private String phone; // 电话

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", phone='" + phone + '\'' +
                '}';
    }
}
