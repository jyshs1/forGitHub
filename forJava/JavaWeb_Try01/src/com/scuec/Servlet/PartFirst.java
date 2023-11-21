package com.scuec.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
//Servlet核心代码

public class PartFirst extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //request设置编码
        request.setCharacterEncoding("utf-8");
        String userName = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        System.out.println("userName = " + userName);
        System.out.println("userPassword = " + userPassword);

//        //设置response缓存区编码为UTF-8编码格式
//        response.setCharacterEncoding("utf-8");
//        //在响应中主动告诉浏览器使用UTF-8编码格式来接收数据
//        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    /**
     * response.setContentType("text/html;charset=UTF-8");
     * 可以替换 上面两句response.setCharacterEncoding("utf-8");
     * */
        PrintWriter out = response.getWriter();
//        out.println("<h1> 用户名：" +  userName + "</h1>");
//        out.println("<h1> 密码： " + userPassword + "</h1>");

        out.println(" 用户名：" + userName);
        out.println(" 密码： " + userPassword);

    }
}
