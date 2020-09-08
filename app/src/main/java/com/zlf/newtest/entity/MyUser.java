package com.zlf.newtest.entity;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.entity
 * 文件名：    MyUser
 * 创建者：    ZLF
 * 创建时间:   2020/5/6 0006 0:51
 * 描述：      用户属性
 */

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {
    private Integer age;
    private  Boolean sex;
    private String desc;
    private Integer ScenicAdminis;
    private Integer SystemAdminis;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }


    public Integer getScenicAdminis() {
        return ScenicAdminis;
    }

    public void setScenicAdminis(Integer scenicAdminis) {
        ScenicAdminis = scenicAdminis;
    }

    public Integer getSystemAdminis() {
        return SystemAdminis;
    }

    public void setSystemAdminis(Integer systemAdminis) {
        SystemAdminis = systemAdminis;
    }
}
