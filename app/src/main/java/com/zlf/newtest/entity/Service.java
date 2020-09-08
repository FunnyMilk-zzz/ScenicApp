package com.zlf.newtest.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.entity
 * 文件名：    Service
 * 创建者：    ZLF
 * 创建时间:   2020/6/3 0003 18:38
 * 描述：      吃喝玩乐服务
 */
public class Service extends BmobObject implements Serializable {
    private String scenicId;
    private String messageId;
    private String serviceTitle;
    private String serviceName;
    private String price;

    public String getScenicId() {
        return scenicId;
    }

    public void setScenicId(String scenicId) {
        this.scenicId = scenicId;
    }

    public String getServiceTitle() {
        return serviceTitle;
    }

    public void setServiceTitle(String serviceTitle) {
        this.serviceTitle = serviceTitle;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
