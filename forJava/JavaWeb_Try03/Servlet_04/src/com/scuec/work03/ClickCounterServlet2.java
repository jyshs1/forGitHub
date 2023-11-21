package com.scuec.work03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月29日 20:36
 * 项目名称:  JavaWeb_Try03
 * 文件名称:  ClickCounterServlet
 * 文件描述:  @Description: 统计点击次数
 */
@WebServlet("/counter")
public class ClickCounterServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setContentType("text/html; charset=utf-8");

        // 获取会话
        HttpSession session = request.getSession();
        Integer clickCounter = (Integer) session.getAttribute("clickCounter");
        //没有，则赋新值
        if (session.getAttribute("clickCounter") == null) {
            clickCounter = 1;
            session.setAttribute("clickCounter", clickCounter);
        } else {
            session.setAttribute("clickCounter", (clickCounter + 1));
        }

/*        // 获取session
        HttpSession session = request.getSession();

        //session五分钟内有效
        session.setMaxInactiveInterval(60 * 5);*/

        //将登陆的次数显示在页面上
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>\r\n" +
                "<html>\r\n" +
                "<head>\r\n" +
                "<meta charset=\"UTF-8\">\r\n" +
                "<title>登陆网页次数统计</title>\r\n" +
                "</head>\r\n" +
                "<body>");
        out.print("<h1>");
        out.print("这是您今天第 " + session.getAttribute("clickCounter") + "次访问");
        out.print("<h1>");
        out.print("</body>\r\n" +
                "</html>");
    }
}
