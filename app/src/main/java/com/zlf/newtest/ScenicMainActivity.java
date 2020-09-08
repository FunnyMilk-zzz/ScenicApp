package com.zlf.newtest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zlf.newtest.fragment.UserFragment;
import com.zlf.newtest.fragment.scAdmin_GuideFragment;
import com.zlf.newtest.fragment.scAdmin_MessageFragment;
import com.zlf.newtest.fragment.scAdmin_OrderFragment;
import com.zlf.newtest.fragment.scAdmin_ScenicFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest
 * 文件名：    ScenicMainActivity
 * 创建者：    ZLF
 * 创建时间:   2020/5/9 0009 1:21
 * 描述：      景区管理员主活动
 */
public class ScenicMainActivity extends AppCompatActivity {
    //声明
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    //4个title
    private List<String> mTitle;
    private List<Fragment> mFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scenic_admin);
        //去掉标题栏阴影
        getSupportActionBar().setElevation(0);

        initData();
        initView();
    }

    private void initData() {
        mTitle = new ArrayList<>();

        mTitle.add("资讯管理");
        mTitle.add("景区管理");
        mTitle.add("导游管理");
        mTitle.add("订单管理");
        mTitle.add("个人中心");

        mFragment = new ArrayList<>();
        mFragment.add(new scAdmin_MessageFragment());
        mFragment.add(new scAdmin_ScenicFragment());
        mFragment.add(new scAdmin_GuideFragment());
        mFragment.add(new scAdmin_OrderFragment());
        mFragment.add(new UserFragment());
    }

    private void initView() {
        mTablayout = findViewById(R.id.mScenic_Tablayout);
        mViewPager = findViewById(R.id.mScenic_ViewPager);

        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

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
        mTablayout.setupWithViewPager(mViewPager);
    }
}
