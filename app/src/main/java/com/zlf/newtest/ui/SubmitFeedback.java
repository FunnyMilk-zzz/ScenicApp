package com.zlf.newtest.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Feedback;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui
 * 文件名：    SubmitFeedback
 * 创建者：    ZLF
 * 创建时间:   2020/5/30 0030 2:51
 * 描述：      反馈信息提交
 */
public class SubmitFeedback extends  BaseActivity implements View.OnClickListener {
    private EditText et_title;
    private EditText et_body;
    private Button btn_submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitfeedback);

        initView();
    }

    private void initView(){
        et_title = findViewById(R.id.ac_submitFeedback_tx_title);
        et_body = findViewById(R.id.ac_submitFeedback_tx_body);

        btn_submit = findViewById(R.id.ac_submitFeedback_btn_submit);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_submitFeedback_btn_submit:
                String title = et_title.getText().toString().trim();
                String body = et_body.getText().toString().trim();

                //判断输入框是否为空

                if (!TextUtils.isEmpty(title) & !TextUtils.isEmpty(body)){

                    //存入feedback中
                    final Feedback feedback = new Feedback();
                    feedback.setTitle(title);
                    feedback.setBody(body);

                    feedback.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e  == null){
                                toShow("反馈信息提交成功");
                                finish();
                            }else {
                                toShow("发布失败"+e.toString());
                            }
                        }
                    });
                }else {
                    toShow("输入框不能为空嗷");
                }
                break;
        }
    }

    private void toShow(String s){
        Toast.makeText(SubmitFeedback.this,s,Toast.LENGTH_SHORT).show();
    }
}
