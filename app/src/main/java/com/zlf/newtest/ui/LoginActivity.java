package com.zlf.newtest.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.zlf.newtest.MainActivity;
import com.zlf.newtest.R;
import com.zlf.newtest.entity.MyUser;
import com.zlf.newtest.ui.adminUI.LoginActivity_ScenicAdmin;
import com.zlf.newtest.ui.adminUI.LoginActivity_SystemAdmin;
import com.zlf.newtest.utils.ShareUtils;
import com.zlf.newtest.view.CustomDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui
 * 文件名：    LoginActivity
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 22:35
 * 描述：      登录
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //按钮
   private TextView btn_registered;
    private EditText et_name;
    private EditText et_password;
    private Button btnLogin;
    private CheckBox keep_password;

    private TextView tv_forget;

    private CustomDialog dialog;

    private TextView tv_ScenicAdmin;
    private TextView tv_SystemAdmin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){
        btn_registered = findViewById(R.id.btn_registered);
        btn_registered.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        keep_password = (CheckBox) findViewById(R.id.keep_password);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_forget.setOnClickListener(this);

        //初始化跳转管理员
        tv_ScenicAdmin = findViewById(R.id.tv_login_ScenicAdmin);
        tv_ScenicAdmin.setOnClickListener(this);

        //初始化跳转系统管理员
        tv_SystemAdmin = findViewById(R.id.tv_login_SystemAdmin);
        tv_SystemAdmin.setOnClickListener(this);

        //初始化自定义的dialog
        dialog = new CustomDialog(this,100,100,R.layout.dialog_loding,R.style.Theme_dialog,Gravity.CENTER,R.style.pop_anim_style);
        //屏幕外点击无效
        dialog.setCancelable(false);

        //设置忘记密码选中状态
        boolean isCheck =  ShareUtils.getBoolean(this,"keeppass",false);
        keep_password.setChecked(isCheck);
        if(isCheck){
            //设置密码
            et_name.setText(ShareUtils.getString(this,"name",""));
            et_password.setText(ShareUtils.getString(this,"password",""));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_forget:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.btn_registered:
                Intent intent = new Intent(this,RegisteredActivity.class);
                startActivity(intent);
                break;

                //跳转到景区管理员
            case R.id.tv_login_ScenicAdmin:
                Intent intent1 = new Intent(this, LoginActivity_ScenicAdmin.class);
                startActivity(intent1);
                finish();
                break;
                //跳转到系统管理员
            case R.id.tv_login_SystemAdmin:
                Intent intent2 = new Intent(this, LoginActivity_SystemAdmin.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.btnLogin: //登录
                //获取输入框的值
                String name = et_name.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                //判断输入框是否是空
                if(!TextUtils.isEmpty(name)& ! TextUtils.isEmpty(password)){
                //不为空就执行登录 弹出dialog
                    dialog.show();
                    final MyUser user = new MyUser();
                    user.setUsername(name);
                    user.setPassword(password);
                    user.login(new SaveListener<MyUser>(){
                        //重写回调方法
                        @Override
                        public void done(MyUser myUser, BmobException e) {
                            //屏蔽dialog
                            dialog.dismiss();
                            //判断结果
                            if(e==null){
                            //判断邮箱是否验证，需要验证才能登录
                                if(user.getEmailVerified()){
                                    //跳转到主页
                                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(LoginActivity.this,"请去邮箱点击验证邮件",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(LoginActivity.this,"登录失败"+e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this,"输入框不能为空哦",Toast.LENGTH_SHORT).show();
                }
        }
    }

    //如果只输入用户名和密码但是没有点击登录，直接退出活动
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //保存当前选择状态
        ShareUtils.putBoolean(this,"keeppass",keep_password.isChecked());
        //是否记住密码
        if(keep_password.isChecked()){
            //记住用户名和密码
            ShareUtils.putString(this,"name",et_name.getText().toString().trim());
            ShareUtils.putString(this,"password",et_password.getText().toString().trim());
        }else{
            ShareUtils.deleShare(this,"name");
            ShareUtils.deleShare(this,"password");
        }
    }
}
