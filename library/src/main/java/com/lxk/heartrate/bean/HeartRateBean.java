package com.lxk.heartrate.bean;

/**
 * @author https://github.com/103style
 * @date 2020/8/11 16:49
 */
public class HeartRateBean {
    /**
     * 每天，每月显示时用
     */
    public int max, min;
    /**
     * 时间对应显示的下标
     * 从 1 开始计算
     */
    public int index;
    /**
     * 心率
     */
    public int heartRate;

    public HeartRateBean(int heartRate, int index) {
        this.index = index;
        this.heartRate = heartRate;
    }

    public HeartRateBean(int max, int min, int index) {
        this.max = max;
        this.min = min;
        this.index = index;
    }
}
