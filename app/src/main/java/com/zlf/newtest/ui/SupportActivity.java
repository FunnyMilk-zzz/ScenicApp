package com.zlf.newtest.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zlf.newtest.R;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui
 * 文件名：    SupportActivity
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 19:36
 * 描述：      个人设置
 */
public class SupportActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_call;
    private TextView tv_submitFeedback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        initView();
    }

    private void initView(){
        tv_call = findViewById(R.id.ac_support_tx_call);
        tv_submitFeedback = findViewById(R.id.ac_support_tx_submitFeedback);

        //call点击事件
        tv_call.setOnClickListener(this);

        //提交反馈信息点击事件
        tv_submitFeedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_support_tx_call:
                callPhone("18452483511");
                break;

            case R.id.ac_support_tx_submitFeedback:
                startActivity(new Intent(this,SubmitFeedback.class));
                break;
        }
    }

    //打电话方法
    private void callPhone(String s){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + s ));
        startActivity(intent);
    }
}
