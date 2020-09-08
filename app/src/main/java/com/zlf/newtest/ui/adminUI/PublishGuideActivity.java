package com.zlf.newtest.ui.adminUI;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Guide;
import com.zlf.newtest.ui.BaseActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui.adminUI
 * 文件名：    PublishGuideActivity
 * 创建者：    ZLF
 * 创建时间:   2020/5/8 0008 1:11
 * 描述：      TODO
 */
public class PublishGuideActivity extends BaseActivity implements View.OnClickListener {

    //声明编辑框
    private EditText tv_name;
    private EditText tv_sex;
    private EditText tv_age;
    private EditText tv_city;
    private EditText tv_hobby;
    private EditText tv_introduce;
    private EditText tv_worktime;
    private EditText tv_duty;
    private EditText tv_isOrder;
    private EditText tv_price;
    private EditText tv_contactInformation;
    private Button btn_submit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_guide);
        initView();
    }

    private void initView() {
        tv_name = findViewById(R.id.apublish_guide_et_name);
        tv_sex = findViewById(R.id.apublish_guide_et_sex);
        tv_age = findViewById(R.id.apublish_guide_et_age);
        tv_city = findViewById(R.id.apublish_guide_et_city);
        tv_hobby = findViewById(R.id.apublish_guide_et_hobby);
        tv_introduce = findViewById(R.id.apublish_guide_et_introduce);
        tv_worktime = findViewById(R.id.apublish_guide_et_wordTime);
        tv_duty = findViewById(R.id.apublish_guide_et_duty);
        tv_isOrder = findViewById(R.id.apublish_guide_et_isOrder);
        tv_price = findViewById(R.id.apublish_guide_et_price);
        tv_contactInformation = findViewById(R.id.apublish_guide_et_contactInformation);

        btn_submit = findViewById(R.id.apublish_guide_btn_submit);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.apublish_guide_btn_submit:
                //获取编辑框的值
                String name = tv_name.getText().toString().trim();
                String sex= tv_sex.getText().toString().trim();
                Integer age= Integer.parseInt(tv_age.getText().toString().trim());
                String city= tv_city.getText().toString().trim();
                String hobby= tv_hobby.getText().toString().trim();
                String introduce= tv_introduce.getText().toString().trim();
                String worktime= tv_worktime.getText().toString().trim();
                String duty= tv_duty.getText().toString().trim();
                String isOrder= tv_isOrder.getText().toString().trim();
                Integer price= Integer.parseInt(tv_price.getText().toString().trim());
                String contactInformation= tv_contactInformation.getText().toString().trim();

                //判断输入框是否为空
                if (!TextUtils.isEmpty(name) &
                        !TextUtils.isEmpty(sex) &
                        age!=0 &
                        !TextUtils.isEmpty(city) &
                        !TextUtils.isEmpty(hobby) &
                        !TextUtils.isEmpty(introduce) &
                        !TextUtils.isEmpty(worktime) &
                        !TextUtils.isEmpty(duty) &
                        !TextUtils.isEmpty(isOrder) &
                        price!=0 &
                        !TextUtils.isEmpty(contactInformation)){

                    //存
                    final Guide guide = new Guide();
                    guide.setName(name);
                    guide.setSex(sex);
                    guide.setAge(age);
                    guide.setCity(city);
                    guide.setHobby(hobby);
                    guide.setIntroduce(introduce);
                    guide.setWorkTime(worktime);
                    guide.setDuty(duty);
                    guide.setIsOrder(isOrder);
                    guide.setPrice(price);
                    guide.setContactInformation(contactInformation);

                    guide.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e==null){
                                tostShow("发布成功");
                                finish();
                            }else {
                                tostShow("发布失败"+e.toString());
                            }
                        }
                    });
                }else {
                    tostShow("输入框不能为空");
                }

                break;
        }
    }

    public void tostShow(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
