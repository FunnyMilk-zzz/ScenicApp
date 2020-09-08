package com.zlf.newtest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui
 * 文件名：    ChangePassword
 * 创建者：    ZLF
 * 创建时间:   2020/5/6 0006 18:11
 * 描述：      更改密码
 */
public class ChangePassword extends BaseActivity implements View.OnClickListener {

    private EditText et_now;
    private EditText et_new;
    private EditText et_new_password;
    private Button btn_update_password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        initView();
    }

    private void initView() {
        et_now = (EditText) findViewById(R.id.et_now);
        et_new = (EditText) findViewById(R.id.et_new);
        et_new_password = (EditText) findViewById(R.id.et_new_password);

        btn_update_password = (Button) findViewById(R.id.btn_update_password);
        btn_update_password.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_password:
                //1,获取输入框值
                String now = et_now.getText().toString().trim();
                String news = et_new.getText().toString().trim();
                String new_password = et_new_password.getText().toString();

                //2.判断输入框是否为空
                if(!TextUtils.isEmpty(now) & !TextUtils.isEmpty(news) & !TextUtils.isEmpty(new_password)){
                    //3.判断两次新密码是否一致
                    if (news.equals(new_password)){
                        //4.重置密码
                        MyUser.updateCurrentUserPassword(now, news, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null){
                                    Toast.makeText(ChangePassword.this,"重置密码成功，请重新登录",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ChangePassword.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();

                                }else {
                                    Toast.makeText(ChangePassword.this,"重置失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"输入框不能为空哦",Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }
}
