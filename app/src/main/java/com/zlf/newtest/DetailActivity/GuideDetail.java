package com.zlf.newtest.DetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.zlf.newtest.DetailActivity.Pay.PayGuide;
import com.zlf.newtest.R;
import com.zlf.newtest.adapter.DetailGuideAdapter;
import com.zlf.newtest.entity.Guide;
import com.zlf.newtest.ui.BaseActivity;
import com.zlf.newtest.view.CustomDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.DetailActivity
 * 文件名：    GuideDetail
 * 创建者：    ZLF
 * 创建时间:   2020/5/8 0008 22:47
 * 描述：      导游详情
 */
public class GuideDetail extends BaseActivity implements View.OnClickListener {
    //声明
    private ListView mListView;
    private Guide guide;
    private List<Guide> mList = new ArrayList<>();
    private DetailGuideAdapter detailGuideAdapter;

    //声明按钮
    Button payBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_guide);
        initView();
    }
    private void initView(){
        getBundle();//取传过来的对象
        mListView = findViewById(R.id.listView_detail_guide);
        mList.add(guide);
        detailGuideAdapter = new DetailGuideAdapter(this,mList);
        mListView.setAdapter(detailGuideAdapter);



        //初始化btn
        payBtn = findViewById(R.id.item_Guide_detail_btn_submit);
        payBtn.setOnClickListener(this);
    }

    //获取GuideFragment中传过来的Guide对象
    private void getBundle(){
        Intent intent = getIntent();
        //实例化Bundle
        Bundle bundle = intent.getExtras();
        guide = (Guide) bundle.getSerializable("guideEntity");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.item_Guide_detail_btn_submit:
                //点击预约按钮将Guide对象保存到bundle并发送给PayActivity
                Intent intent = new Intent(this,PayGuide.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("guideEntity",guide);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
