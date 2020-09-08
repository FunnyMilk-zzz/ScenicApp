package com.zlf.newtest.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.TestLooperManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zlf.newtest.MainActivity;
import com.zlf.newtest.R;
import com.zlf.newtest.utils.ShareUtils;
import com.zlf.newtest.utils.StaticClass;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui
 * 文件名：    SplashActivity
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 20:34
 * 描述：      闪屏页
 */
public class SplashActivity extends AppCompatActivity {
    /**
     *
     * 1.延时2000ms
     * 2.判断程序是否第一次运行
     * 3.自定义字体
     * 4.Activity全屏主题
     *
     */

    private TextView tv_splash;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否是第一次运行
                    if(isFirst()){
                        startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                    }else{
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    //初始化View
    private void initView(){
        //延迟2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH,2000);
        tv_splash = findViewById(R.id.tv_splash);

        //设置字体

    }

    //判断方法
    private boolean isFirst(){
        boolean isFirst = ShareUtils.getBoolean(this,StaticClass.SHARE_IS_FIRST,true);
        if(isFirst){
            //是第一次运行,把第一次运行的标记改为否
            ShareUtils.putBoolean(this,StaticClass.SHARE_IS_FIRST,false);
            return  true;
        }else {//否则
            return false;
        }
    }

    //禁止使用返回键
    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
    }
}
