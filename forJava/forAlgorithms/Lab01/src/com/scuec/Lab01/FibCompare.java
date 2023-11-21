package com.scuec.Lab01;

/**
 * 创建人:  @author xxxxxx
 * 创建时间:  2023年10月12日 19:21
 * 项目名称:  forAlgorithms
 * 文件名称:  FibCompare
 * 文件描述:  @Description: 封装比较
 */
public class FibCompare<T> {


    private long executionCount;
    private T totalTime;
    private long fibNum;
    private  int n;


    public  FibCompare(long executionCount, T totalTime, long fibNum, int n) {
        this.executionCount = executionCount;
        this.totalTime = totalTime;
        this.fibNum = fibNum;
        this.n = n;
    }

    public long getExecutionCount() {
        return executionCount;
    }

    public void setExecutionCount(int executionCount) {
        this.executionCount = executionCount;
    }

    public T getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(T totalTime) {
        this.totalTime = totalTime;
    }

    public long getFibNum() {
        return fibNum;
    }

    public void setFibNum(long fibNum) {
        this.fibNum = fibNum;
    }

    @Override
    public String toString() {
        return "FibCompare{" +
                "执行次数=" + executionCount +
                ", 执行时间=" + totalTime +
                "s,  第"+n+"个数=" + fibNum +
                '}';
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
