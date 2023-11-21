package com.suece.servlet;

import com.suece.myssm.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月18日 16:50
 * 项目名称:  JavaWeb_Try02
 * 文件名称:  ZPart
 * 文件描述:  @Description: 测试测试
 */
@WebServlet("/demo01")
public class ZPart extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进来了");
        System.out.println("11111111111------------22222222222222");

        HttpSession session = request.getSession() ;
        session.setAttribute("helloSessionAttr", "helloSessionAttr-VALUE");
        super.processTemplate("index2",request,response);
    }
}
