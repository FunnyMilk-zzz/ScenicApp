package com.zlf.newtest.DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.DetailActivity.Pay.PayService;
import com.zlf.newtest.R;
import com.zlf.newtest.adapter.DetailMessageAdapter;
import com.zlf.newtest.adapter.MessageAdapter;
import com.zlf.newtest.entity.Message;
import com.zlf.newtest.entity.Service;
import com.zlf.newtest.ui.BaseActivity;
import com.zlf.newtest.utils.L;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.DetailActivity
 * 文件名：    MessageDetail
 * 创建者：    ZLF
 * 创建时间:   2020/5/8 0008 19:07
 * 描述：      资讯详情
 */
public class MessageDetail extends BaseActivity implements View.OnClickListener {
    //声明list
    private ListView mListView;
    private Message message;
    private List<Message> mList = new ArrayList<>();
    private DetailMessageAdapter detailMessageAdapter;
    private Service service = new Service();

    //声明控件
    private TextView tx_serviceTitle;
    private TextView  tx_itemName;
    private TextView  tx_price;
    private Button btn_book;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_message);

        initView();
    }
    private void initView(){
        //初始化控件
        tx_serviceTitle = findViewById(R.id.ac_detail_message_itemTitle);
        tx_itemName = findViewById(R.id.ac_detail_message_itemName);
        tx_price = findViewById(R.id.ac_detail_message_itemPrice);
        btn_book = findViewById(R.id.ac_detail_message_btn_book);

        getBundle();//取传过来的对象

        //设置标题
        String title = message.getMessageTitle();
        getSupportActionBar().setTitle(title);
        //填满ListView
        mListView = findViewById(R.id.listView_detail_message);
        mList.add(message);
        detailMessageAdapter = new DetailMessageAdapter(this,mList);
        mListView.setAdapter(detailMessageAdapter);
        getItem();//获取服务项目

        //点击预定按钮支付
        btn_book.setOnClickListener(this);
    }

    //获取messageFragment中传过来的Message对象
    private void getBundle(){

        Intent intent = getIntent();
        //实例化Bundle
        Bundle bundle = intent.getExtras();
        //获取Message对象的数据
        message = (Message) bundle.getSerializable("messageEntity");
    }

    //获取该资讯下的游玩服务
    public void getItem(){
        //获取资讯id
       final String messageId =  message.getObjectId();
        BmobQuery<Service> bmobQuery = new BmobQuery<Service>();
       bmobQuery.addWhereEqualTo("messageId",messageId).findObjects(new FindListener<Service>() {
           @Override
           public void done(List<Service> list, BmobException e) {
               if (e == null){
                   service = list.get(0);
                   //填内容到控件中
                   tx_serviceTitle.setText(service.getServiceTitle());
                   tx_itemName.setText(service.getServiceName());
                   tx_price.setText(service.getPrice());
               }else {
                   tostShow("服务信息获取错误"+e.toString());
               }
           }
       });

    }

    private void tostShow(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_detail_message_btn_book:
                Intent intent =  new Intent(this, PayService.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Service",service);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
