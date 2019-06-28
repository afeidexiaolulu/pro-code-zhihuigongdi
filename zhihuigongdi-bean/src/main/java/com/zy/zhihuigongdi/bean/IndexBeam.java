package com.zy.zhihuigongdi.bean;

/**
 * 前台柱状图及环形图Beam
 * 2019-06-18
 * 陈坤鹏
 */
public class IndexBeam {
    Integer number; //人员数量
    String type;    //类型
    String percentage;  //百分比
    String color;   //颜色

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
