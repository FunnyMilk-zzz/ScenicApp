package com.zlf.newtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.zlf.newtest.fragment.MessageFragment;
import com.zlf.newtest.fragment.GuideFragment;
import com.zlf.newtest.fragment.ScenicFragment;
import com.zlf.newtest.fragment.UserFragment;
import com.zlf.newtest.ui.LocationGpsActivity;
import com.zlf.newtest.ui.SupportActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //TabLayout
    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //Title
    private List<String> mTitle;
    //Fragment
    private List<Fragment>mFragment;
    //悬浮窗
    private FloatingActionButton fab_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //去掉标题栏阴影
        getSupportActionBar().setElevation(0);
        
        //初始化数据
        initData();
        initView();




    }

    //初始化数据方法
    private void initData() {
        mTitle = new ArrayList<>();
        mTitle.add("旅游资讯");//照道理来说要用strings.xml文件里的中文字符不然容易报错，来不及了先这么写
        mTitle.add("景区信息");
        mTitle.add("导游预约");
        mTitle.add("个人中心");

        //初始化fragment
        mFragment = new ArrayList<>();
        mFragment.add(new MessageFragment());
        mFragment.add(new ScenicFragment());
        mFragment.add(new GuideFragment());
        mFragment.add(new UserFragment());
    }

    //初始化view方法
    private void initView(){
        //悬浮按钮相关
        fab_setting = findViewById(R.id.fab_gps);
        fab_setting.setOnClickListener(this);

        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = findViewById(R.id.mViewPager);
        //预加载view
        mViewPager.setOffscreenPageLimit(mFragment.size());

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            //选中的item
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            //返回个数
            @Override
            public int getCount() {
                return mFragment.size();
            }

            //设置标题

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });

        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_gps:
                startActivity(new Intent(this, LocationGpsActivity.class));
                break;
        }
    }
}
