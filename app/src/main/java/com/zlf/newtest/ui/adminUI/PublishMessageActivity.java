package com.zlf.newtest.ui.adminUI;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Message;
import com.zlf.newtest.ui.BaseActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui
 * 文件名：    PublishMessageActivity
 * 创建者：    ZLF
 * 创建时间:   2020/5/7 0007 3:06
 * 描述：      资讯发布
 */
public class PublishMessageActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_title;
    private EditText et_author;
    private EditText et_imgurl;
    private EditText et_scid;//景区id
    private EditText et_body;
    private EditText et_introduce;
    private Button btnSubmit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_message);

        initView();

    }

    private void initView() {
        et_title = findViewById(R.id.apublic_message_et_title);
        et_author = findViewById(R.id.apublic_message_et_author);
        et_imgurl = findViewById(R.id.apublic_message_et_imgurl);
        et_scid = findViewById(R.id.apublic_message_et_scid);
        et_body = findViewById(R.id.apublic_message_et_body);
        et_introduce = findViewById(R.id.apublic_message_et_introduce);

        btnSubmit = findViewById(R.id.apublic_message_btn_submit);
        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.apublic_message_btn_submit:
                //获取输入框值
                String title = et_title.getText().toString().trim();
                String author = et_author.getText().toString().trim();
                String imgurl = et_imgurl.getText().toString().trim();
                String scid = et_scid.getText().toString().trim();
                String introduce = et_introduce.getText().toString().trim();
                String body = et_body.getText().toString();//正文不需要屏蔽空格



                //判断输入框是否为空
                if(!TextUtils.isEmpty(title) &
                        !TextUtils.isEmpty(author) &
                        !TextUtils.isEmpty(title) &
                        !TextUtils.isEmpty(imgurl) &
                        !TextUtils.isEmpty(scid) &
                        !TextUtils.isEmpty(body) &
                        !TextUtils.isEmpty(introduce)){

                    //存入message中
                    final Message message = new Message();
                    message.setMessageTitle(title);
                    message.setAuthor(author);
                    message.setIntroduce(introduce);
                    message.setImgUrl(imgurl);
                    message.setScenicUrl(scid);
                    message.setMessageBody(body);
                    message.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e == null){
                                Toast.makeText(PublishMessageActivity.this,"发布成功",Toast.LENGTH_LONG).show();
                                finish();
                            }else {
                                Toast.makeText(PublishMessageActivity.this,"发布失败"+e.toString(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(this,"输入框不能为空哦",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
