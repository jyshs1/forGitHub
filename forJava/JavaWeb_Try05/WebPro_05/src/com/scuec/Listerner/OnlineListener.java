package com.scuec.Listerner;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月14日 16:27
 * 项目名称:  JavaWeb_Try05
 * 文件名称:  OnlineListener
 * 文件描述:  @Description: 统计在线人数
 */
@WebListener()
public class OnlineListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent evt) {
        // HttpSessionListener.super.sessionCreated(evt);
        HttpSession session = evt.getSession();
        String current =
                (String) session.getServletContext().getAttribute("online");
        if (current == null) {
            current = "0";
        }

        int c = Integer.parseInt(current) + 1;
        current = String.valueOf(c);
        session.getServletContext().setAttribute("online", current);
        System.out.println("current = " + current);
        String his = (String) session.getServletContext().getAttribute("Counter");
        if (his == null) {
            his = "0";
        }
        int total = Integer.parseInt(his) + 1;
        his = String.valueOf(total);
        session.getServletContext().setAttribute("Counter", his);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent evt) {
        // HttpSessionListener.super.sessionDestroyed(evt);
        HttpSession session = evt.getSession();
        String current =
                (String) session.getServletContext().getAttribute("online");
        if (current == null) {
            current = "0";
        }
        int c = Integer.parseInt(current) - 1;
        current = String.valueOf(c);
        session.getServletContext().setAttribute("online", current);
    }
}

