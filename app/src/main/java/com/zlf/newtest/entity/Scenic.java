package com.zlf.newtest.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.entity
 * 文件名：    Scenic
 * 创建者：    ZLF
 * 创建时间:   2020/5/7 0007 23:30
 * 描述：      景区
 */
public class Scenic extends BmobObject implements Serializable {
    private String title;
    private String scenicName;
    private String introduce;
    private String imgurl;
    private String gps;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }
}
