package com.zlf.newtest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zlf.newtest.DetailActivity.GuideDetail;
import com.zlf.newtest.DetailActivity.MessageDetail;
import com.zlf.newtest.R;
import com.zlf.newtest.adapter.GuideAdapter;
import com.zlf.newtest.entity.Guide;
import com.zlf.newtest.entity.Scenic;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.fragment
 * 文件名：    GuideFragment
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 19:15
 * 描述：      导游页面
 */
public class GuideFragment extends Fragment {
    //声明list
    private ListView mListView;
    private List<Guide> mList = new ArrayList<>();
    private GuideAdapter guideAdapter ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide,null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        mListView = (ListView) view.findViewById(R.id.listView_guide);
        //查询数据库内的数据
        BmobQuery<Guide> query = new BmobQuery<>();
        query.order("-updatedAt");//升查询
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

        //设置mListView点击事件实现页面跳转,并将bundle内存的导游对象发给Detail.class中
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GuideDetail.class);
                Guide guide = new Guide();
                guide = mList.get(position);
                //实例化bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("guideEntity",guide);
                intent.putExtras(bundle);
                startActivity(intent);
                Toast.makeText(getActivity(), "跳转到导游详情", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void tostShow(String s){
        Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
    }
}
