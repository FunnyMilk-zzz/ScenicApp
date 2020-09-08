package com.zlf.newtest.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.entity
 * 文件名：    Feedback
 * 创建者：    ZLF
 * 创建时间:   2020/5/30 0030 2:35
 * 描述：      反馈信息实体
 */
public class Feedback extends BmobObject implements Serializable {

    private String title;
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
