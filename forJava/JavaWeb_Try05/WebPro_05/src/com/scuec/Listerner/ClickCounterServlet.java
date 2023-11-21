package com.scuec.Listerner;

import javax.servlet.ServletContext;
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
public class ClickCounterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //编码设置
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setContentType("text/html; charset=utf-8");

        // 获取上下文对象
        ServletContext servletContext = getServletContext();
        Integer clickCounter = (Integer) servletContext.getAttribute("clickCounter");
        //没有，则赋新值
        if (servletContext.getAttribute("clickCounter") == null) {
            clickCounter = 1;
            servletContext.setAttribute("clickCounter", clickCounter);
        } else {
            servletContext.setAttribute("clickCounter", (clickCounter + 1));
        }
        //获取对话
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60);
        Integer sessionCount = (Integer) session.getAttribute("sessionCount");
        //没有，则赋新值
        if (session.getAttribute("sessionCount") == null) {
            sessionCount = 1;
            session.setAttribute("sessionCount", sessionCount);
        } else {
            session.setAttribute("sessionCount", (sessionCount + 1));
        }

/*        //打印总浏览量
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>\r\n" +
                "<html>\r\n" +
                "<head>\r\n" +
                "<meta charset=\"UTF-8\">\r\n" +
                "<title>登陆网页次数统计</title>\r\n" +
                "</head>\r\n" +
                "<body>");
        out.print("<h1>");
        out.print("你是第 " + servletContext.getAttribute("clickCounter") + "位访客");
        out.print("<h1>\r\n");

        out.print("<h1>");
        out.print("这是你 " + session.getAttribute("sessionCount") + "次访问");
        out.print("<h1>\r\n");

        out.print("</body>\r\n" +
                "</html>");*/
    }
}
