package com.zlf.newtest.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.entity
 * 文件名：    Order
 * 创建者：    ZLF
 * 创建时间:   2020/5/9 0009 19:59
 * 描述：      TODO
 */
public class Order extends BmobObject implements Serializable {
    private String kinds;
    private String goodsId;
    private String bookDate;
    private String userId;
    private Boolean payIs;
    private String sum;


    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getPayIs() {
        return payIs;
    }

    public void setPayIs(Boolean payIs) {
        this.payIs = payIs;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
