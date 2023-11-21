package com.scuec.Listerner;

import com.scuec.utils.CounterFile;
//
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月29日 21:21
 * 项目名称:  JavaWeb_Try03
 * 文件名称:  CountListener
 * 文件描述:  @Description: 计数监听
 */
@WebListener
public class CountListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //监听上下文 读取文件里的访问历史记录量
        try {
            //创建文件操作对象
            CounterFile counterFile = new CounterFile();
            //CounterFile.getLastCount() 函数会从硬盘读取上次记录的历史访问量
            //并返回这个数字
            Integer lastCount = counterFile.getLastCount();
            //放到上下文作用域
            sce.getServletContext().setAttribute("clickCounter", lastCount);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        //创建文件操作对象
        CounterFile counterFile = new CounterFile();
        //读取服务器记录的总的访问量
        Integer clickCounter = (Integer) sce.getServletContext().getAttribute("clickCounter");
        try {
            //CounterFile.setLastCount(Integer) 会将传入的整数写到磁盘
            //以便下次服务器启动时， 可以直接承接上一次记录的访问量
            counterFile.setLastCount(clickCounter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

