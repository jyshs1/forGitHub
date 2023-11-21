package com.scuec.servlet;

import com.scuec.myssm.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月17日 23:28
 * 项目名称:  JavaWeb_Try02
 * 文件名称:  BeginPart
 * 文件描述:  @Description: 开始部分
 */
@WebServlet("/sss/login.do")
public class ShowPart extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("你好");

        System.out.println("进来了");
        for (int i = 0; i < 10; i++) {
            System.out.printf(i + " ");
        }
        HttpSession session = request.getSession();

        String isPasswordSaveStr = (String) session.getAttribute("isPasswordSave");
        String userName = null;
        String userPassword = null;
        if (isPasswordSaveStr.equals("ture")) {
            //index页面 读cookie时 发现保存了账户与密码 且未超时
            userPassword = (String) session.getAttribute("userPassword");
            userName = (String) session.getAttribute("userName");
        }
        //super.processTemplate("index2",request,response);
    }
}
