//package com.scuec.work03;
//
//import com.scuec.utils.CounterFile;
//import com.scuec.utils.JDBCUtils;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * 创建人:  @author xxxxxx
// * 创建时间:  2023年10月29日 21:21
// * 项目名称:  JavaWeb_Try03
// * 文件名称:  CountListener
// * 文件描述:  @Description: 计数监听
// */
//@WebListener
//public class CountListener implements ServletContextListener {
//
//
//    private boolean flagDB = false;
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        System.out.println("进来了");
//        System.out.println("进来了");
//        System.out.println("进来了");
//        System.out.println("进来了");
//
//        try {
//            CounterFile counterFile = new CounterFile();
//            Integer lastCount = counterFile.getLastCount();
//            sce.getServletContext().setAttribute("clickCounter", lastCount);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//
//
//        CounterFile counterFile = new CounterFile();
//        Integer clickCounter = (Integer) sce.getServletContext().getAttribute("clickCounter");
//
//        try {
//            counterFile.setLastCount(clickCounter);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}
//
