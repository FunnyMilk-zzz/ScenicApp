package com.zlf.newtest.ui.adminUI;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.ScenicAdmin;
import com.zlf.newtest.ui.BaseActivity;
import com.zlf.newtest.ui.RegisteredActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui.adminUI
 * 文件名：    RegisteredActivity_ScenicAdmin
 * 创建者：    ZLF
 * 创建时间:   2020/5/6 0006 20:50
 * 描述：      TODO
 */
public class RegisteredActivity_ScenicAdmin extends BaseActivity implements View.OnClickListener {
    private EditText et_user;
    private EditText et_pass;
    private EditText et_password;
    private EditText et_email;
    private Button btnRegistered;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic_admin_register);
        initView();
    }

    private void initView() {
        et_user = (EditText) findViewById(R.id.scenic_admin_et_user);
        et_pass = (EditText) findViewById(R.id.scenic_admin_et_pass);
        et_password = (EditText) findViewById(R.id.scenic_admin_et_password);
        et_email = (EditText) findViewById(R.id.scenic_admin_et_email);
        btnRegistered = (Button) findViewById(R.id.scenic_admin_btnRegistered);
        btnRegistered.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scenic_admin_btnRegistered:
                //获取输入框的值
                String name = et_user.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                //判断是否为空 前面加了感叹号 非
                if(!TextUtils.isEmpty(name) &
                        !TextUtils.isEmpty(pass) &
                        !TextUtils.isEmpty(password) &
                        !TextUtils.isEmpty(email)){

                    //判断两次输入的密码是否一致
                    if(pass.equals(password)){
                        //注册
                        ScenicAdmin sAdmin = new ScenicAdmin();
                        sAdmin.setUsername(name);
                        sAdmin.setPassword(password);
                        sAdmin.setEmail(email);
                        sAdmin.signUp(new SaveListener<ScenicAdmin>() {
                            @Override
                            public void done(ScenicAdmin scenicAdmin, BmobException e) {
                                if (e == null) {
                                    Toast.makeText(RegisteredActivity_ScenicAdmin.this,"注册成功",Toast.LENGTH_LONG).show();
                                    finish();
                                }else {
                                    Toast.makeText(RegisteredActivity_ScenicAdmin.this,"失败: " + e.toString(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(this,"两次输入的密码不一样哦",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"输入框不能为空嗷",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
