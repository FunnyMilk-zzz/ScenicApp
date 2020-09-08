package com.zlf.newtest.ui.adminUI;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
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
import com.zlf.newtest.ScenicMainActivity;
import com.zlf.newtest.entity.MyUser;
import com.zlf.newtest.entity.ScenicAdmin;
import com.zlf.newtest.ui.ForgetPasswordActivity;
import com.zlf.newtest.ui.LoginActivity;
import com.zlf.newtest.utils.ShareUtils;
import com.zlf.newtest.view.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui
 * 文件名：    LoginActivity_ScenicAdmin
 * 创建者：    ZLF
 * 创建时间:   2020/5/6 0006 20:14
 * 描述：      景区管理员登录
 */
public class LoginActivity_ScenicAdmin extends AppCompatActivity implements View.OnClickListener {
  //  private TextView btn_registered;
    private EditText et_name;
    private EditText et_password;
    private Button btnLogin;
    private CheckBox keep_password;

    private CustomDialog dialog;

    private TextView tv_forget;

    int flag = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic_admin_login);

        initView();
    }

    private void initView(){
//        btn_registered = findViewById(R.id.scenic_admin_btn_registered);
//        btn_registered.setOnClickListener(this);
        et_name = (EditText) findViewById(R.id.scenic_admin_et_name);
        et_password = (EditText) findViewById(R.id.scenic_admin_et_password);
        btnLogin = (Button) findViewById(R.id.scenic_admin_btnLogin);
        btnLogin.setOnClickListener(this);
        keep_password = (CheckBox) findViewById(R.id.scenic_admin_keep_password);
        tv_forget = (TextView) findViewById(R.id.scenic_admin_tv_forget);
        tv_forget.setOnClickListener(this);

        //初始化自定义的dialog
        dialog = new CustomDialog(this,100,100,R.layout.dialog_loding,R.style.Theme_dialog, Gravity.CENTER,R.style.pop_anim_style);
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

    //判断是否是管理员的方法

    private void isScenicAdmin(final String name, final String password){

        BmobQuery<ScenicAdmin> eq1 = new BmobQuery<ScenicAdmin>();
        //条件1 用户名为输入的时候
        eq1.addWhereEqualTo("username",name);
        //条件2 是否是景区管理员
        BmobQuery<ScenicAdmin> eq2 = new BmobQuery<ScenicAdmin>();
        eq2.addWhereEqualTo("ScenicAdminis",1);
        //组装条件
        List<BmobQuery<ScenicAdmin>> andQuerys = new ArrayList<BmobQuery<ScenicAdmin>>();


        andQuerys.add(eq1);
        andQuerys.add(eq2);

        //查询这个and条件
        BmobQuery<ScenicAdmin> query = new BmobQuery<ScenicAdmin>();
        query.and(andQuerys);
        query.findObjects(new FindListener<ScenicAdmin>() {
            @Override
            public void done(List<ScenicAdmin> list, BmobException e) {
                if (e==null){
                    //判断list是否为空
                    if(list.size()== 0 ){
                        flag = 0;
                    }else if(list.size()!=0 ){
                        flag = 1;


                    }
                    //调用账户判断方法进行接下来的步骤
                    panduan(name,password);
                }else {
                   Toast.makeText(LoginActivity_ScenicAdmin.this,"查询出错",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void panduan(String name , String password){
        final ScenicAdmin sAdmin = new ScenicAdmin();
        sAdmin.setUsername(name);
        sAdmin.setPassword(password);
        sAdmin.login(new SaveListener<ScenicAdmin>() {
            @Override
            public void done(ScenicAdmin scenicAdmin, BmobException e) {
                //屏蔽dialog
                dialog.dismiss();
                //判断结果
                if(e==null){
                    //判断邮箱是否验证，需要验证才能登录
                    if(sAdmin.getEmailVerified()){
                        //判断是否是管理员
                        if(flag == 1 ){
                            //跳转到主页
                            Toast.makeText(LoginActivity_ScenicAdmin.this,"登录成功",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity_ScenicAdmin.this, ScenicMainActivity.class); ////////////后面写跳转到admin的主界面
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity_ScenicAdmin.this,"验证未通过，不是景区管理员",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginActivity_ScenicAdmin.this,"验证未通过,请激活邮箱",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity_ScenicAdmin.this,"登录失败："+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scenic_admin_tv_forget:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
//            case R.id.scenic_admin_btn_registered:
//                startActivity(new Intent(this, RegisteredActivity_ScenicAdmin.class));
//                break;
            case R.id.scenic_admin_btnLogin: //登录
                //获取输入框的值
                 String name = et_name.getText().toString().trim();
                 String password = et_password.getText().toString().trim();

                //判断输入框是否是空
                if(!TextUtils.isEmpty(name)& ! TextUtils.isEmpty(password)){
                    dialog.show();
                    //登录判断
                    isScenicAdmin(name,password);


                }else {
                    Toast.makeText(this,"输入框不能为空哦",Toast.LENGTH_SHORT).show();
                }
                break;
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
