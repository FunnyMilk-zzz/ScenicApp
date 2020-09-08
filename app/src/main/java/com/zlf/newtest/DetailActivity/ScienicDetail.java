package com.zlf.newtest.DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.DetailActivity.Pay.PayTicket;
import com.zlf.newtest.MainActivity;
import com.zlf.newtest.R;
import com.zlf.newtest.adapter.DetailScenicAdapter;
import com.zlf.newtest.adapter.ScenicTicketAdapter;
import com.zlf.newtest.entity.Scenic;
import com.zlf.newtest.entity.Ticket;
import com.zlf.newtest.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.DetailActivity
 * 文件名：    ScienicDetail
 * 创建者：    ZLF
 * 创建时间:   2020/5/8 0008 22:04
 * 描述：      景区详情
 */
public class ScienicDetail extends BaseActivity  {
    //声明
    private ListView mListView;
    private Scenic scenic;
    private List<Scenic> mList = new ArrayList<>();
    private DetailScenicAdapter detailScenicAdapter;

    //声明门票相关
    private ListView mListViewTicket;
    private List<Ticket> mListTicket = new ArrayList<>();
    private ScenicTicketAdapter scenicTicketAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scenic);

        initView();
    }

    private void initView(){
        getBundle();//取传过来的对象

        //设置标题
        String title = scenic.getTitle();
        getSupportActionBar().setTitle(title);

        //填充listview

        mListView = findViewById(R.id.listView_detail_scenic);
        mList.add(scenic);
        detailScenicAdapter = new DetailScenicAdapter(this,mList);
        mListView.setAdapter(detailScenicAdapter);

        //填充门票listview
        mListViewTicket = findViewById(R.id.listView_detail_scenic_ticket);
        getTicket(scenic.getObjectId());

        //预定A按钮点击事件


    }

    //获取messageFragment中传过来的Message对象
    private void getBundle(){
        Intent intent = getIntent();
        //实例化Bundle
        Bundle bundle = intent.getExtras();
        //获取Message对象的数据
        scenic = (Scenic) bundle.getSerializable("scenicEntitiy");
    }

    //获取门票信息
    private void getTicket(String s){
        BmobQuery<Ticket> eq1 = new BmobQuery<Ticket>();
        eq1.addWhereEqualTo("scenicId",s);//条件1，判断是不是这个景区id

        BmobQuery<Ticket> eq2 = new BmobQuery<Ticket>();
        eq2.order("-updateAt");//时间降序查询

        List<BmobQuery<Ticket>> andQuerys = new ArrayList<BmobQuery<Ticket>>();
        andQuerys.add(eq1);
        andQuerys.add(eq2);

        //查询符合整个and条件的人
        BmobQuery<Ticket> query = new BmobQuery<Ticket>();
        query.and(andQuerys);
        query.findObjects(new FindListener<Ticket>() {
            @Override
            public void done(List<Ticket> list, BmobException e) {


                if(e == null){
                    mListTicket = list;
                    scenicTicketAdapter = new ScenicTicketAdapter(ScienicDetail.this,mListTicket,mListener);
                    mListViewTicket.setAdapter(scenicTicketAdapter);


                }else {
                    tostShow("查询失败"+e.toString());
                }
            }
        });

    }

    public void tostShow(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }



    //响应item点击事件


    private ScenicTicketAdapter.MyClickListener mListener = new ScenicTicketAdapter.MyClickListener() {
        @Override
        public void myOnClick(int position, View v) {
            switch (v.getId()){
                case R.id.item_Scenic_ticket_btnA:
                    int a = position;
                    Ticket ticket = mListTicket.get(a);
                    Intent intent = new Intent(ScienicDetail.this, PayTicket.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Ticket",ticket);
                    bundle.putSerializable("itemKind","A");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.item_Scenic_ticket_btnB:
                 //   tostShow("b");
                    int b = position;
                    Ticket ticketB = mListTicket.get(b);
                    Intent intentB = new Intent(ScienicDetail.this, PayTicket.class);
                    Bundle bundleB = new Bundle();
                    bundleB.putSerializable("Ticket",ticketB);
                    bundleB.putSerializable("itemKind","B");
                    intentB.putExtras(bundleB);
                    startActivity(intentB);
                    break;
            }
        }
    };
}
