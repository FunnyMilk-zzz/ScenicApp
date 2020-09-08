package com.zlf.newtest.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.entity
 * 文件名：    Ticket
 * 创建者：    ZLF
 * 创建时间:   2020/5/10 0010 18:23
 * 描述：      TODO
 */
public class Ticket extends BmobObject implements Serializable {
    private String ticketName;
    private String scenicId;
    private String itemA;
    private Integer aPrice;
    private String itemB;
    private Integer bPrice;

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getScenicId() {
        return scenicId;
    }

    public void setScenicId(String scenicId) {
        this.scenicId = scenicId;
    }

    public String getItemA() {
        return itemA;
    }

    public void setItemA(String itemA) {
        this.itemA = itemA;
    }

    public Integer getaPrice() {
        return aPrice;
    }

    public void setaPrice(Integer aPrice) {
        this.aPrice = aPrice;
    }

    public String getItemB() {
        return itemB;
    }

    public void setItemB(String itemB) {
        this.itemB = itemB;
    }

    public Integer getbPrice() {
        return bPrice;
    }

    public void setbPrice(Integer bPrice) {
        this.bPrice = bPrice;
    }
}
