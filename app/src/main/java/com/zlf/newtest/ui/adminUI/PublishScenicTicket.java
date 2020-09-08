package com.zlf.newtest.ui.adminUI;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Ticket;
import com.zlf.newtest.ui.BaseActivity;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui.adminUI
 * 文件名：    PublishScenicTicket
 * 创建者：    ZLF
 * 创建时间:   2020/5/10 0010 18:21
 * 描述：      发布门票
 */
public class PublishScenicTicket extends BaseActivity implements View.OnClickListener {

    //声明控件
    private EditText et_scenicId;
    private EditText et_ticketName;
    private EditText et_itemA;
    private EditText et_itemB;
    private EditText et_aPrice;
    private EditText et_bPrice;
    private Button   btnSubmit;
    final Ticket ticket = new Ticket();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_scenic_ticket);

        initView();
    }

    private void initView(){
        et_scenicId = findViewById(R.id.apublish_scenic_ticket_scenicId);
        et_ticketName = findViewById(R.id.apublish_scenic_ticketName);
        et_itemA = findViewById(R.id.apublish_scenic_ticket_itemA);
        et_itemB = findViewById(R.id.apublish_scenic_ticket_itemB);
        et_aPrice = findViewById(R.id.apublish_scenic_ticket_aPrice);
        et_bPrice = findViewById(R.id.apublish_scenic_ticket_bPrice);
        btnSubmit = findViewById(R.id.apublish_scenic_ticket_btn_submit);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.apublish_scenic_ticket_btn_submit:
                publish();
                break;
        }
    }

    private void publish(){
        String scenicId =  et_scenicId.getText().toString().trim();
        String ticketName = et_ticketName.getText().toString().trim();
        String itemA =  et_itemA.getText().toString().trim();
        String itemB = et_itemB.getText().toString().trim();
        Integer aPrice = Integer.parseInt(et_aPrice.getText().toString().trim());
        Integer bPrice = Integer.parseInt(et_bPrice.getText().toString().trim());

        //判断输入框是否为空
        if(!TextUtils.isEmpty(scenicId) &
                !TextUtils.isEmpty(ticketName) &
                !TextUtils.isEmpty(itemA) &
                !TextUtils.isEmpty(itemB) &
                aPrice != 0 &
                bPrice != 0 ){

            //存入Ticket中
            ticket.setScenicId(scenicId);
            ticket.setTicketName(ticketName);
            ticket.setItemA(itemA);
            ticket.setItemB(itemB);
            ticket.setaPrice(aPrice);
            ticket.setbPrice(bPrice);

            ticket.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null){
                        tostShow("发布门票信息成功");
                        finish();
                    }else {
                        tostShow("发布失败"+e.toString());
                    }
                }
            });

        }else {
            tostShow("输入框不能为空");
        }

    }
    public void tostShow(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
