package com.zlf.newtest.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;


import com.zlf.newtest.R;
import com.zlf.newtest.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui
 * 文件名：    RegisteredActivity
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 22:47
 * 描述：      注册
 */
public class RegisteredActivity extends BaseActivity implements View.OnClickListener{

    private EditText et_user;
    private EditText et_age;
    private EditText et_desc;
    private RadioGroup mRadioGroup;
    private EditText et_pass;
    private EditText et_password;
    private EditText et_email;
    private Button btnRegistered;
    //性别 默认是第一个选项
    private boolean isGender = true;

    private int sAdminflag = 0;
    private int systemAdminFlag = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        initView();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegistered:
                //获取输入框的值
                String name = et_user.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();
                String pass = et_pass.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String email = et_email.getText().toString().trim();

                //判断是否为空 前面加了感叹号 非
                if(!TextUtils.isEmpty(name) &
                        !TextUtils.isEmpty(age) &
                        !TextUtils.isEmpty(pass) &
                        !TextUtils.isEmpty(password) &
                        !TextUtils.isEmpty(email)){

                    //判断两次输入的密码是否一致
                    if(pass.equals(password)){
                        //先判断性别SEX
                        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (checkedId == R.id.rb_boy) {
                                    isGender = true;
                                } else if (checkedId == R.id.rb_girl) {
                                    isGender = false;
                                }
                            }
                        });

                        //判断简介是否为空
                        if(TextUtils.isEmpty(desc)){
                            desc="这个人很懒，什么都没有留下";
                        }

                        //注册 ***********
                        MyUser user = new MyUser();
                        user.setUsername(name);
                        user.setPassword(password);
                        user.setEmail(email);
                        user.setAge(Integer.parseInt(age));
                        user.setSex(isGender);
                        user.setDesc(desc);

                        //默认管理员属性为0不是管理员
                        user.setScenicAdminis(sAdminflag);
                        user.setSystemAdminis(systemAdminFlag);

                        user.signUp(new SaveListener<MyUser>() {

                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                //请求成功
                                if (e == null) {
                                    Toast.makeText(RegisteredActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(RegisteredActivity.this,"失败: " + e.toString(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }else {
                        Toast.makeText(this,"两次输入的密码不一样嗷，铁铁",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this,"输入框不能为空嗷",Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void initView() {
        et_user = (EditText) findViewById(R.id.et_user);
        et_age = (EditText) findViewById(R.id.et_age);
        et_desc = (EditText) findViewById(R.id.et_desc);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_password = (EditText) findViewById(R.id.et_password);
        et_email = (EditText) findViewById(R.id.et_email);
        btnRegistered = (Button) findViewById(R.id.btnRegistered);
        btnRegistered.setOnClickListener(this);
    }
}
