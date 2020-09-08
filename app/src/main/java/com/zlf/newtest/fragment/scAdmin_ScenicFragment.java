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
import com.zlf.newtest.adapter.ScenicAdapter;
import com.zlf.newtest.entity.Scenic;
import com.zlf.newtest.ui.adminUI.PublishScenicActivity;
import com.zlf.newtest.ui.adminUI.PublishScenicTicket;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.fragment
 * 文件名：    scAdmin_ScenicFragment
 * 创建者：    ZLF
 * 创建时间:   2020/5/9 0009 1:31
 * 描述：      景区管理
 */
public class scAdmin_ScenicFragment extends Fragment implements View.OnClickListener {
    //声明list
    private ListView mListView;
    private List<Scenic> mList = new ArrayList<>();

    private ScenicAdapter scenicAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scadmin_scenic,null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        //发布按钮跳转
        Button pubBtn = view.findViewById(R.id.fScAdmin_scenic_btn_pub_msg);
        pubBtn.setOnClickListener(this);

        Button pubTicket = view.findViewById(R.id.fScAdmin_scenic_btn_pub_ticket);
        pubTicket.setOnClickListener(this);

        mListView = (ListView) view.findViewById(R.id.fScAdmin_scenic_listView);

        //查询数据库内的数据
        BmobQuery<Scenic> query = new BmobQuery<>();
        query.order("-updatedAt");//降序查询
        query.findObjects(new FindListener<Scenic>() {
            @Override
            public void done(List<Scenic> list, BmobException e) {
                if(e == null){
                  //  tostShow("加载成功，共"+list.size()+"条信息");
                    mList = list;
                    scenicAdapter = new ScenicAdapter(getActivity(),mList);
                    mListView.setAdapter(scenicAdapter);
                }else {
                    tostShow("景区信息加载失败"+e.toString());
                }
            }
        });
    }

    private void tostShow(String s){
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fScAdmin_scenic_btn_pub_msg:
                startActivity(new Intent(getActivity(), PublishScenicActivity.class));
                break;
            case R.id.fScAdmin_scenic_btn_pub_ticket:
                startActivity(new Intent(getActivity(), PublishScenicTicket.class));
                break;
        }
    }
}
