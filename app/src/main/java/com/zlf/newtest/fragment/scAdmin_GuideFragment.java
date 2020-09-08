package com.zlf.newtest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zlf.newtest.R;
import com.zlf.newtest.adapter.GuideAdapter;
import com.zlf.newtest.entity.Guide;
import com.zlf.newtest.ui.adminUI.PublishGuideActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.fragment
 * 文件名：    scAdmin_GuideFragment
 * 创建者：    ZLF
 * 创建时间:   2020/5/9 0009 1:52
 * 描述：      TODO
 */
public class scAdmin_GuideFragment extends Fragment implements View.OnClickListener {
    //声明list
    private ListView mListView;
    private List<Guide> mList = new ArrayList<>();
    private GuideAdapter guideAdapter ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_syadmin_guide,null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        //发布按钮跳转
        Button btn = view.findViewById(R.id.fSyAdmin_guide_btn_pub_msg);
        btn.setOnClickListener(this);

        mListView = (ListView) view.findViewById(R.id.fSystem_guide_listView);
        //查询数据库内的数据
        BmobQuery<Guide> query = new BmobQuery<>();
        query.order("-updatedAt");//jiang查询
        query.findObjects(new FindListener<Guide>() {
            @Override
            public void done(List<Guide> list, BmobException e) {
                if(e == null){
                  //  tostShow("加载成功，共"+list.size()+"条信息");
                    mList = list;
                    guideAdapter = new GuideAdapter(getActivity(),mList);
                    mListView.setAdapter(guideAdapter);
                }else {
                    tostShow("导游信息加载失败"+e.toString());
                }
            }
        });
    }

    private void tostShow(String s){
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fSyAdmin_guide_btn_pub_msg:
                startActivity(new Intent(getActivity(), PublishGuideActivity.class));
                break;
        }
    }
}
