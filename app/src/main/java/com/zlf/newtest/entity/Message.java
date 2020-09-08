package com.zlf.newtest.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.entity
 * 文件名：    Message
 * 创建者：    ZLF
 * 创建时间:   2020/5/7 0007 1:57
 * 描述：      资讯实体类
 */

/*
旅游资讯
	旅游资讯编号*
	标题
	正文
	配图
	作者
	时间
	景区链接
 */
public class Message extends BmobObject implements Serializable {
    private String messageTitle;
    private String author;
    private String introduce;
    private String imgUrl;
    private String scenicUrl;
    private String messageBody;


    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getScenicUrl() {
        return scenicUrl;
    }

    public void setScenicUrl(String scenicUrl) {
        this.scenicUrl = scenicUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
