package com.zlf.newtest.utils;

import android.util.Log;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.utils
 * 文件名：    L
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 19:52
 * 描述：      Log封装类
 */
public class L {
    //开关
    public static final boolean DEBUG = true;

    //TAG
    public static final String TAG = "newTestApp";

    //等级 DIWEF
    public static void d(String text){
        if(DEBUG){
            Log.d(TAG,text);
        }
    }

    public static void i(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }
    public static void w(String text){
        if(DEBUG){
            Log.w(TAG,text);
        }
    }
    public static void e(String text){
        if(DEBUG){
            Log.e(TAG,text);
        }
    }
    public static void f(String text){
        if(DEBUG){
            Log.wtf(TAG,text);
        }
    }
}
