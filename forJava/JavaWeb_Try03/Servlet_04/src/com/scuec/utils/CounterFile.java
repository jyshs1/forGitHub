package com.scuec.utils;

import java.io.*;
/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月29日 22:56
 * 项目名称:  JavaWeb_Try03
 * 文件名称:  CounterFile
 * 文件描述:  @Description: 文件读写
 */
public class CounterFile {
    private Integer lastCount;

    public Integer getLastCount() throws IOException {

        String dir = "F:\\GitHub\\forJava\\JavaWeb_Try03\\Servlet_03\\CounterFile.txt";
        File file = new File(dir);
        if (!file.exists())
            file.createNewFile();

//创建BufferedReader读取文件内容
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        br.close();
        if(line != null) {
            lastCount = Integer.parseInt(line);
        } else if(line == ""){
            lastCount = Integer.parseInt(line);
        } else {
            lastCount = 1;
        }

        return lastCount;
    }

    public void setLastCount(Integer lastCount) throws IOException {
        String dir = "F:\\GitHub\\forJava\\JavaWeb_Try03\\Servlet_03\\CounterFile.txt";
        File file = new File(dir);
        if (!file.exists())
            file.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(lastCount.toString());
        bw.flush();
        bw.close();
        this.lastCount = lastCount;
    }
}
