
package com.scuec.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年11月14日 15:39
 * 项目名称:  JavaWeb_Try05
 * 文件名称:  LoginFilter
 * 文件描述:  @Description: 登录拦截
 */

/**
 * Todo:过滤直接访问 SuccessPage.jsp 的请求
 *       并将其返回到登陆页面
 */

@WebFilter("/homeWork/SuccessPage.jsp")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //编码与类型转换
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        //根据Login.do 页面的session比对，如果数据库内匹配上，则session.getAttribute() 返回true
        //证明这次访问请求是来自已经登录用户
        // 如果返回是空，或者false 说明这不是登录用户，不能访问登陆页面
        String isTrueUser = (String)session.getAttribute("isTrueUser");
        if (isTrueUser == null) {
            isTrueUser = "fales";   //之前未登录，直接访问登陆页面
        }
        if (isTrueUser.equals("true")) {    // 为输入正确密码与用户名的用户
            //这种用户放行
            filterChain.doFilter(request,response);
        } else {    // 否则为  直接访问的用户，不放行
            PrintWriter out = response.getWriter();
            out.print("<script language='javascript'>" +
                    "alert('你还未登录！');" +
                    "window.location.href='../index';</script>')");
        }
    }
}

