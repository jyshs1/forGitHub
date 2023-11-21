package com.scuec.fruit.servlets;

import com.scuec.fruit.dao.FruitDAO;
import com.scuec.fruit.dao.impl.FruitDAOImpl;
import com.scuec.fruit.pojo.Fruit;
import com.scuec.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月10日 21:43
 * 项目名称:  StudyWeb_01
 * 文件名称:  IndexServlet
 * 文件描述:  @Description: servlet
 */
@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进去了进去了11111111111");
        FruitDAO fruitDAO = new FruitDAOImpl();
        System.out.println("进去了进去了");
        List<Fruit> fruitList = fruitDAO.getFruitList();
        System.out.println("数据库访问成功");
        HttpSession session = request.getSession();
        session.setAttribute("fruitList",fruitList);

        super.processTemplate("index",request,response);
        //response.sendRedirect("/index");
    }
}
