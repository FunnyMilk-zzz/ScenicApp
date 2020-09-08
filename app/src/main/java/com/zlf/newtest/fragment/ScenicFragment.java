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

import com.zlf.newtest.DetailActivity.ScienicDetail;
import com.zlf.newtest.R;
import com.zlf.newtest.adapter.MessageAdapter;
import com.zlf.newtest.adapter.ScenicAdapter;
import com.zlf.newtest.entity.Message;
import com.zlf.newtest.entity.Scenic;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.fragment
 * 文件名：    ScenicFragment
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 19:14
 * 描述：      景点信息页面
 */
public class ScenicFragment extends Fragment {
    //声明list
    private ListView mListView;
    private List<Scenic> mList = new ArrayList<>();

    private ScenicAdapter scenicAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenic,null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        mListView = (ListView) view.findViewById(R.id.listView_scenic);

        //查询数据库内的数据
        BmobQuery<Scenic> query = new BmobQuery<>();
        query.order("-updatedAt");//降序查询
        query.findObjects(new FindListener<Scenic>() {
            @Override
            public void done(List<Scenic> list, BmobException e) {
                if(e == null){
                  //  tostShow("加载成功，共"+list.size()+"条信息");

//                    for (int i = 0 ; i<list.size();i++){  //旧的先留着万一有用呢
//                        Scenic s = list.get(i);
//                        mList.add(s);
//                        scenicAdapter = new ScenicAdapter(getActivity(),mList);
//                        mListView.setAdapter(scenicAdapter);
//                    }
                    mList = list; //这里是重写的添加adapter
                    scenicAdapter = new ScenicAdapter(getActivity(),mList);
                    mListView.setAdapter(scenicAdapter);
                }else {
                    tostShow("景区信息加载失败"+e.toString());
                }
            }
        });

        //设置mListView跳转
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ScienicDetail.class);
                Scenic scenic = new Scenic();
                scenic = mList.get(position);
                //实例化Bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("scenicEntitiy",scenic);
                intent.putExtras(bundle);
                startActivity(intent);
             //   tostShow("跳转到景区详情页面");
            }
        });

    }

    //toast方法,之前的模块都没写，显得很乱，现在用这个重写方法
    private void tostShow(String s){
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }
}