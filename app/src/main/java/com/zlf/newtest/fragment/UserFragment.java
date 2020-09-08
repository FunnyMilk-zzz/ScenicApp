package com.zlf.newtest.fragment;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.MyUser;
import com.zlf.newtest.ui.ChangePassword;
import com.zlf.newtest.ui.LocationGpsActivity;
import com.zlf.newtest.ui.LoginActivity;
import com.zlf.newtest.ui.SupportActivity;
import com.zlf.newtest.ui.UserOrderActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.fragment
 * 文件名：    UserFragment
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 19:15
 * 描述：      用户主页
 */
public class UserFragment extends Fragment implements View.OnClickListener {

    private Button btn_exit_user;
    private TextView edit_user;

    private EditText et_username;
    private EditText et_sex;
    private EditText et_age;
    private EditText et_desc;

    //定位和我的订单
    private TextView tv_support;
    private TextView btn_myOrder;
    //更新按钮
    private Button btn_update_ok;


    //忘记密码按钮
    private TextView btn_changePassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);

        findView(view);
        return view;
    }

    //初始化view

    private void findView(View view) {
        btn_exit_user = (Button) view.findViewById(R.id.btn_exit_user);
        btn_exit_user.setOnClickListener(this);
        btn_exit_user = (Button) view.findViewById(R.id.btn_exit_user);
        btn_exit_user.setOnClickListener(this);
        edit_user = (TextView) view.findViewById(R.id.edit_user);
        edit_user.setOnClickListener(this);

        et_username = (EditText) view.findViewById(R.id.et_username);
        et_sex = (EditText) view.findViewById(R.id.et_sex);
        et_age = (EditText) view.findViewById(R.id.et_age);
        et_desc = (EditText) view.findViewById(R.id.et_desc);

        btn_update_ok = (Button) view.findViewById(R.id.btn_update_ok);
        btn_update_ok.setOnClickListener(this);

        //gps点击事件
        tv_support = view.findViewById(R.id.fUser_tv_support);
        tv_support.setOnClickListener(this);

        //以下编辑框默认不可点击和输入
        setEnabled(false);

        //设置编辑框具体的值
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        et_username.setText(userInfo.getUsername());
        et_age.setText(userInfo.getAge() + "");
        et_sex.setText(userInfo.getSex() ? "男" : "女");
        et_desc.setText(userInfo.getDesc());

        //忘记密码
        btn_changePassword = view.findViewById(R.id.fuser_btn_changepassword);
        btn_changePassword.setOnClickListener(this);

        //myorder
        btn_myOrder = view.findViewById(R.id.fUser_myOrder);
        btn_myOrder.setOnClickListener(this);

    }

    //控制焦点
    private void setEnabled(boolean is) {
        et_username.setEnabled(is);
        et_sex.setEnabled(is);
        et_age.setEnabled(is);
        et_desc.setEnabled(is);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //我的订单
            case R.id.fUser_myOrder:
                startActivity(new Intent(getActivity(), UserOrderActivity.class));
                break;
            //gps按钮
            case R.id.fUser_tv_support:
                startActivity(new Intent(getActivity(), SupportActivity.class));
                break;
            case R.id.btn_exit_user:
                //推出登录按钮
                //清楚缓存用户对象
                MyUser.logOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                Toast.makeText(getActivity(), "退出登录成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_user:  //编辑资料
                setEnabled(true);
                btn_update_ok.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_update_ok:
                //取输入框的值
                //1.拿到输入框的值
                String username = et_username.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String sex = et_sex.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();

                //2.判断是否为空
                if (!TextUtils.isEmpty(username) & !TextUtils.isEmpty(age) & !TextUtils.isEmpty(sex)) {
                    //3.更新属性,将内容放到库中
                    MyUser user = new MyUser();
                    user.setUsername(username);
                    user.setAge(Integer.parseInt(age));
                    //性别
                    if (sex.equals("男")) {
                        user.setSex(true);
                    } else {
                        user.setSex(false);
                    }
                    //简介
                    if (!TextUtils.isEmpty(desc)) {
                        user.setDesc(desc);
                    } else {
                        user.setDesc("这个人很懒，什么都没有留下！");
                    }

                    int a = Integer.parseInt(age);
                    if (checkAge(a)) {
                        BmobUser bmobUser = BmobUser.getCurrentUser(MyUser.class);
                        user.update(bmobUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    //修改成功
                                    setEnabled(false);
                                    btn_update_ok.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), "修改个人信息成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "年龄输入有误，请确认输入（范围1~100）", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "输入框不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
            //修改密码
            case R.id.fuser_btn_changepassword:
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
                break;
        }
    }

    private boolean checkAge(int age) {
        if (age < 100 & age > 1) {
            return true;
        } else {
            return false;
        }
    }
}
