package com.zlf.newtest.ui.adminUI;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Scenic;
import com.zlf.newtest.ui.BaseActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui.adminUI
 * 文件名：    PublishScenicActivity
 * 创建者：    ZLF
 * 创建时间:   2020/5/7 0007 23:27
 * 描述：      发布景区信息
 */
public class PublishScenicActivity extends BaseActivity implements View.OnClickListener {

    //声明
    private EditText et_title;
    private EditText et_name;
    private EditText et_imgurl;
    private EditText et_introduce;
    private EditText et_gps;
    private Button btnSubmit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_scenic);
        initView();
    }

    private void initView() {
        //实例化
         et_title = findViewById(R.id.apublish_scenic_et_title);
         et_name = findViewById(R.id.apublish_scenic_et_name);
         et_imgurl = findViewById(R.id.apublish_scenic_et_imgurl);
         et_introduce = findViewById(R.id.apublish_scenic_et_introduce);
         et_gps = findViewById(R.id.apublish_scenic_et_gps);
         btnSubmit = findViewById(R.id.apublish_scenic_btn_submit);
         btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.apublish_scenic_btn_submit:
                String title = et_title.getText().toString();
                String name = et_name.getText().toString().trim();
                String introduce = et_introduce.getText().toString();
                String imgurl = et_imgurl.getText().toString().trim();
                String gps = et_gps.getText().toString().trim();

                //判断输入框是否为空
                if(!TextUtils.isEmpty(title) &
                                !TextUtils.isEmpty(name) &
                                !TextUtils.isEmpty(introduce) &
                                !TextUtils.isEmpty(imgurl) &
                                !TextUtils.isEmpty(gps) ){
                    //存入Scenic中
                    final Scenic scenic = new Scenic();
                    scenic.setScenicName(name);
                    scenic.setTitle(title);
                    scenic.setIntroduce(introduce);
                    scenic.setImgurl(imgurl);
                    scenic.setGps(gps);
                    scenic.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e==null){
                                tostShow("景区发布成功");
                                finish();
                            }else {
                                tostShow("发布失败"+e.toString());
                            }
                        }
                    });

                }else {
                    tostShow("输入框不能为空哦");
                }
                break;
        }
    }

    public void tostShow(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
