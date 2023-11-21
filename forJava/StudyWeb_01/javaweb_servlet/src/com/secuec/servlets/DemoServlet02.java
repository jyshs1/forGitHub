package com.secuec.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月10日 20:41
 * 项目名称:  StudyWeb_01
 * 文件名称:  DemoServlet02
 * 文件描述:  @Description: 跳转2
 */
public class DemoServlet02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.printf("这是跳转后的页面");
    }
}
