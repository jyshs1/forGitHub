package com.scuec.servlets;

import com.sun.net.httpserver.HttpsServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Provider;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月12日 08:24
 * 项目名称:  StudyWeb_01
 * 文件名称:  Demo01
 * 文件描述:  @Description: 保存作用域
 */
@WebServlet("/Demo01")
public class Demo01 extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        request.setAttribute("name","iiiiiiiii]");
//        request.getRequestDispatcher("Demo02").forward(request,response);
//
//    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.setAttribute("name","iiiiiiiii]");
        request.getRequestDispatcher("Demo02").forward(request,response);

    }
}
