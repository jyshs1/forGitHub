package com.scuec.servlet;

import com.scuec.myssm.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月17日 23:28
 * 项目名称:  JavaWeb_Try02
 * 文件名称:  BeginPart
 * 文件描述:  @Description: 开始部分
 */
/**
 * Todo: 利用thymeleaf 服务器转发到ShowPart.html页面
 *  ShowPart.html 是整合几个iframe的父页面，其中
 *  LoginPart.jsp   是登录表单页面
 * */
@WebServlet("/index")
public class BeginPart extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // System.out.println("你好");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
/*
//        String inputName = request.getParameter("userName");
//        String inputPassWord = request.getParameter("userPassword");

        Cookie cookieUser = null;
        Cookie cookiePassWord = null;
        Cookie cookieReminder = null;

        Cookie[] cookies = request.getCookies();

//        if (cookies != null) {
//            boolean flag = false;
//            for (int i = 0; i < cookies.length; i++) {
//                if (cookies[i].getName().equals("userName")) {
//                    cookieUser = cookies[i];
//                    int maxAge = cookieUser.getMaxAge();
//                    for (int j = 0; j < cookies.length; j++) {
//                        if (cookies[i].getName().equals("isPasswordSave")) {
//                            cookieReminder = cookies[i];
//                            break;
//                        }
//                    }
//                    break;
//                }
//            }
//        }
        //寻找 之前是否选择了记住密码的 cookie
        HttpSession session = request.getSession();
        boolean isPasswordSave = false;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("isPasswordSave")) {
                    //找到就读取 value 看看是否为true
                        cookieReminder = c;
                    //如果有 存记住密码的cookie 且 为true 保存了密码
                    //那就说明此时 有cookie保存了账户和密码 且cookie未超时
                    if (cookieReminder.getValue().equals("true") && c.getMaxAge() == -1) {
                        isPasswordSave = true;
                        session.setAttribute("isPasswordSave", "ture");
                        //会话域中添加 密码被保存为true 到login页面处理
                    }
                }
            }
        }
        if (!isPasswordSave) {   //上次未设置保存密码 或者cookie 超时
            session.setAttribute("isPasswordSave","false");
        }
        //cookie点了保存账户与密码 去cookies中寻找保存的账号与密码
        String userName = "";
        String userPassword = "";
        if (isPasswordSave) {
        //找到用户名与密码 直接存到session中
            for (Cookie c : cookies) {
                String name = c.getName();
                if (name.equals("userName")) {
                    userName = c.getValue();
                    session.setAttribute("userName",userName);
                } else if (name.equals("userPassword")) {
                    userPassword = c.getValue();
                    session.setAttribute("userPassword",userPassword);
                }
            }
        }
        if (cookieUser != null && cookieReminder != null) {
            String valReminder = cookieReminder.getValue();
            int maxAge = cookieUser.getMaxAge();
            if (valReminder.equals("isPasswordSave") && maxAge == -1) {
            }
        }*/
        //只有 保存密码的cookie 未超时 且 上次有设置保存密码 才会使得会话域传入 账户名与密码


        super.processTemplate("ShowPart",request,response);
    }
}
