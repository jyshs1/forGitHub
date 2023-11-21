package com.scuec.servlet;

import com.scuec.utils.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月18日 18:36
 * 项目名称:  JavaWeb_Try02
 * 文件名称:  Login
 * 文件描述:  @Description: login.do
 */

/**
 * Todo:jsp页面  读取cookies后 如果有cookies 填充给上用户名与密码 没有就不填充
 *       直接  从输入框读入用户名和密码去数据库匹配 失败就什么也不操作，直接重构定向登陆页面
 *    如果数据库密码正确
 *    判断这次是否点了保存密码(即复选框打勾)，如果点了保存密码，且这是第一次登录，没有cookies 需要新建cookies
 *                          如果不是第一次登录，那么就更新cookies里的账号密码为这次输入， 后跳转计科院
 *    如果这次不保存密码(即复选框不打勾)，将之前的cookies遍历cookies将cookies清空，
 *          后跳转 SuccessPage.jsp
 *
 */
@WebServlet("/homeWork/login.do")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        //读取jsp页面里的判断结果
        String isPasswordSave = (String) session.getAttribute("isPasswordSave");

        boolean flagSave = false; //复选框是否打勾
        //先判断这次 复选框 有没有设置账号密码保存 打勾
        String[] checkBoxs = request.getParameterValues("isPasswordSave");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        if (checkBoxs != null) {
            flagSave = checkBoxs[0].equals("true");
        }
        try {
            Connection connection = JDBCUtils.getConnection();
            String sql = "select * from t_student where userName = ? and userPassword = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, userName);
            preparedStatement.setObject(2, userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean flagDB = false;
            if (resultSet.next()) {
                //有结果账号密码正确
                flagDB = true;
            } else { //错误就提示错误 什么也不处理
                //session 清空
                session.removeAttribute("isPasswordSave");
                //数据库查不到说明 账号密码错误 提示密码错误 重定向index
                PrintWriter out = response.getWriter();
                out.print("<script language='javascript'>" +
                        "alert('您输入的账号或密码错误，请重新输入！');" +
                        "window.location.href='../index';</script>')");
            }
            //这次点了保存密码 且 数据库密码正确 新建cookie与否
            if (flagSave && flagDB) {
                Cookie[] cookies = request.getCookies();

                //有cookies的话去更新这次输入的用户名和密码
                if (cookies != null) {
                    for (Cookie c : cookies) {
                        String name = c.getName();
                        if (name.equals("userName")) {
                            c.setValue(userName);
                            response.addCookie(c);
                        } else if (name.equals("userPassword")) {
                            c.setValue(userPassword);
                            response.addCookie(c);
                        }
                    }
                } else {
                    //没有的话去新建cookie
                    // 创建新的cookie 设置新的cookie保存时间
                    Cookie c1 = new Cookie("userName", userName);
                    Cookie c2 = new Cookie("userPassword", userPassword);
                    Cookie c3 = new Cookie("isPasswordSave", "true");
                    //c3.setMaxAge(3600 * 24 * 30);
                    c3.setMaxAge(30 * 60);
                    response.addCookie(c1);
                    response.addCookie(c2);
                    response.addCookie(c3);
                }
                session.getServletContext().setAttribute("isTrueUser","true");
                PrintWriter out = response.getWriter();
                out.print("<script language='javascript'>" +
                        "alert('登录成功，点击跳转');" +
                        "window.location.href='./SuccessPage.jsp?userName=" +userName + "';" +
                        "</script>')");
            }
            if (!flagSave && flagDB) {    //这次不保存 即这次复选框未打勾 那么清空
                //session 清空

                session.removeAttribute("isPasswordSave");

                //cookie清空
                Cookie[] cookies = request.getCookies();
                for (Cookie c : cookies) {
                    c.setValue(null);
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
                session.setAttribute("isTrueUser","true");
                PrintWriter out = response.getWriter();

                out.print("<script language='javascript'>" +
                "alert('登录成功，点击跳转');" +
                "window.location.href='./SuccessPage.jsp?userName=" +userName + "';" +
                        "</script>')");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

//        try {
//            //数据库连接 与准备 查询
//            //读到用户名和密码 去数据库匹配
//            Connection connection = JDBCUtils.getConnection();
//            String sql = "select * from t_student where userName = ? and userPassword = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setObject(1, userName);
//            preparedStatement.setObject(2, userPassword);
//            if (isPasswordSave.equals("true") && flagSave) {
//                //如果这次还打勾 且 cookie 保存了数据未过期 数据库去查询
//                ResultSet resultSet = preparedStatement.executeQuery();
//                //如果数据库有行返回 则说明存在该用户 且 密码对的上
//                if (resultSet.next()) {
//                    //有结果账号密码正确
//                    System.out.println("登陆成功！");
//                } else {
//                    //session 清空
//                    session.removeAttribute("isPasswordSave");
//                    //cookie清空
//                    Cookie[] cookies = request.getCookies();
//                    for (Cookie c : cookies) {
//                        c.setMaxAge(0);
//                        c.setPath("/");
//                        response.addCookie(c);
//                    }
//                    //数据库查不到说明 账号密码错误 提示密码错误 服务器转发到index
//                    PrintWriter out = response.getWriter();
//                    out.print("<script language='javascript'>" +
//                            "alert('您输入的账号或密码错误，请重新输入！');" +
//                            "window.location.href='../index';</script>')");
//                }
//                //
//            } else {
//                //上次未保存或cookie过期
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                //先去数据库 查询
//                // 如果数据库有行返回 则说明存在该用户 且 密码对的上
//                boolean flagDB = false;
//                if (resultSet.next()) {
//                    //有结果账号密码正确
//                    flagDB = true;
//                }
//                //这次点了保存密码 且 数据库密码正确 且 cookie过期或没有 新建cookie
//                if (flagSave && flagDB) {
//                    Cookie[] cookies = request.getCookies();
//                    //没有的话去新建cookie
//                    if (cookies == null)
//                    {
//                        // 创建新的cookie 设置新的cookie保存时间
//                        Cookie c1 = new Cookie("userName", userName);
//                        Cookie c2 = new Cookie("userPassword", userPassword);
//                        Cookie c3 = new Cookie("isPasswordSave", "true");
//                        //c3.setMaxAge(3600 * 24 * 30);
//                        c3.setMaxAge(30);
//                        response.addCookie(c1);
//                        response.addCookie(c2);
//                        response.addCookie(c3);
//                    } else {   //有cookies的话去更新这次输入的用户名和密码
//                        for (Cookie c : cookies) {
//                            String name = c.getName();
//                            if (name.equals("userName")) {
//                                c.setValue(userName);
//                                response.addCookie(c);
//                            } else if (name.equals("userPassword")){
//                                c.setValue(userPassword);
//                                response.addCookie(c);
//                            }
//                        }
//                    }
//                }
//                if (!flagSave) {    //这次不保存 即这次复选框未打勾 那么清空
//                    //session 清空
//
//                    session.removeAttribute("isPasswordSave");
//
//                    //cookie清空
//                    Cookie[] cookies = request.getCookies();
//                    for (Cookie c : cookies) {
//                        c.setValue(null);
//                        c.setMaxAge(0);
//                        response.addCookie(c);
//                    }
//                }
//                PrintWriter out = response.getWriter();
//                if (flagDB) {
//                    //登录跳转
//                    out.print("<script language='javascript'>" +
//                            "alert('登录成功，点击跳转');" +
//                            "window.location.href='https://www.scuec.edu.cn/jky/';</script>')");
//                } else {
//                    out.print("<script language='javascript'>" +
//                            "alert('您输入的账号或密码错误，请重新输入！');" +
//                            "window.location.href='../index';</script>')");
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


