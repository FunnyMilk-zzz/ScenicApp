package com.zlf.newtest.application;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.zlf.newtest.utils.StaticClass;

import android.util.Log;

import java.util.logging.Logger;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.application
 * 文件名：    BaseApplication
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 18:21
 * 描述：      application
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Bmob
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);

        //初始化百度地图
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

    }
}
