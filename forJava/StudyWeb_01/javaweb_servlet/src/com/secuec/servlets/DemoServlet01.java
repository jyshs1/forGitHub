package com.secuec.servlets;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月10日 20:34
 * 项目名称:  StudyWeb_01
 * 文件名称:  DemoServlet01
 * 文件描述:  @Description: 第一个Servlets
 */
public class DemoServlet01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("这是跳转前的网页");
        //服务器内部转发 浏览器不会变化
        request.getRequestDispatcher("Web2New").forward(request,response);

        //客户端重定向 浏览器会变化
        //response.sendRedirect("Web2New");
    }
}
