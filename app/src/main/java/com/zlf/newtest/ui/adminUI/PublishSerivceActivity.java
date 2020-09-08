package com.zlf.newtest.ui.adminUI;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Service;
import com.zlf.newtest.ui.BaseActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui.adminUI
 * 文件名：    PublishSerivceActivity
 * 创建者：    ZLF
 * 创建时间:   2020/6/3 0003 18:24
 * 描述：      景区服务发布
 */
public class PublishSerivceActivity extends BaseActivity implements View.OnClickListener {

    //声明控件
    private EditText et_scenicId;
    private EditText et_messageId;
    private EditText et_title;
    private EditText et_itemName;
    private EditText et_price;
    private Button btn_submit;
    final Service service = new Service();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_service);

        initView();
    }

    private void initView() {
        et_scenicId = findViewById(R.id.ac_publish_service_scenicId);
        et_messageId = findViewById(R.id.ac_publish_service_messageId);
        et_title = findViewById(R.id.ac_publish_service_title);
        et_itemName = findViewById(R.id.ac_publish_service_itemName);
        et_price = findViewById(R.id.ac_publish_service_price);
        btn_submit = findViewById(R.id.ac_publish_service_btn_submit);

        btn_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ac_publish_service_btn_submit:
                publish();
                break;
        }
    }

    private void publish() {
        String scenicId = et_scenicId.getText().toString().trim();
        String messageId = et_messageId.getText().toString().trim();
        String title = et_title.getText().toString().trim();
        String itemName = et_itemName.getText().toString().trim();
        String price = et_price.getText().toString().trim();

        //判断输入框是否为空
        if (!TextUtils.isEmpty(scenicId) &
                !TextUtils.isEmpty(messageId) &
                !TextUtils.isEmpty(title) &
                !TextUtils.isEmpty(itemName) &
                !TextUtils.isEmpty(price)) {

            //存入service中
            service.setScenicId(scenicId);
            service.setMessageId(messageId);
            service.setServiceTitle(title);
            service.setServiceName(itemName);
            service.setPrice(price);

            service.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e==null){
                        tostShow("游玩服务发布成功");
                        finish();
                    }else {
                        tostShow("发布失败"+e.toString());
                    }
                }
            });
        }
    }
    public void tostShow(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
