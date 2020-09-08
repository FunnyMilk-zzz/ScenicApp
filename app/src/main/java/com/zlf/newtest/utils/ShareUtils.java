package com.zlf.newtest.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.utils
 * 文件名：    ShareUtils
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 20:12
 * 描述：      SharePreferences封装 获取和插入字符串、数值、bool、删除单个或全部
 */
public class ShareUtils {

    public static final String NAME = "config";

    //键 值
    public static void putString(Context mContext,String key,String value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }

    //键 默认值
    public static String getString(Context mContext,String key , String defValue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return  sp.getString(key,defValue);
    }

    //键 值
    public static void putInt(Context mContext,String key,int value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }

    //键 默认值
    public static int getInt(Context mContext,String key , int defValue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return  sp.getInt(key,defValue);
    }

    //键 值
    public static void putBoolean(Context mContext,String key,Boolean value){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();
    }

    //键 默认值
    public static Boolean  getBoolean (Context mContext,String key , Boolean defValue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return  sp.getBoolean(key,defValue);
    }

    //封装删除单个功能
    public static  void deleShare(Context mContext,String key){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    //删除 全部
    public static void deleAll(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
