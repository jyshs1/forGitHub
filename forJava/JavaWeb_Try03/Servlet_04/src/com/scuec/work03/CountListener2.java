package com.scuec.work03;

import com.scuec.utils.CounterFile;
import org.apache.catalina.SessionListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月29日 21:21
 * 项目名称:  JavaWeb_Try03
 * 文件名称:  CountListener
 * 文件描述:  @Description: 计数监听
 */
@WebListener
public class CountListener2 implements HttpSessionListener {


    private boolean flagDB = false;

    @Override
    public void sessionCreated(HttpSessionEvent evt) {
        System.out.println("进来了");
        System.out.println("进来了");
        System.out.println("进来了");
        System.out.println("进来了");

/*        try {
            CounterFile counterFile = new CounterFile();
            Integer lastCount = counterFile.getLastCount();
            evt.getSession().setAttribute("clickCounter", lastCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent evt) {
/*        CounterFile counterFile = new CounterFile();
        Integer clickCounter = (Integer) evt.getSession().getAttribute("clickCounter");

        try {
            counterFile.setLastCount(clickCounter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

}

