package com.zlf.newtest.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui
 * 文件名：    BaseActivity
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 18:23
 * 描述：      Activity基类
 */

//    主要任务
//    1.统一的属性
//    2.统一的接口
//    3.统一的方法

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //菜单栏操作
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
             finish();
             break;
        }
        return super.onOptionsItemSelected(item);
    }


}
