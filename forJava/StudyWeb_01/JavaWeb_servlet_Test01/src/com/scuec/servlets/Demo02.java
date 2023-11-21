package com.scuec.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月12日 08:24
 * 项目名称:  StudyWeb_01
 * 文件名称:  Demo01
 * 文件描述:  @Description: 保存作用域
 */
@WebServlet("/Demo02")
public class Demo02 extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        Object name = request.getAttribute("name");
//        System.out.println("你好");
//        System.out.println(name);
//    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Object name = request.getAttribute("name");
        System.out.println("你好");
        System.out.println(name);
    }
}
