package com.zlf.newtest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zlf.newtest.R;
import com.zlf.newtest.adapter.FeedbackAdapter;
import com.zlf.newtest.entity.Feedback;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.fragment
 * 文件名：    syAdmin_FeedbackFragment
 * 创建者：    ZLF
 * 创建时间:   2020/5/9 0009 1:53
 * 描述：      系统管理员反馈信息管理
 */
public class syAdmin_FeedbackFragment extends Fragment {
    //声明LIST
    private ListView mListView;
    private List<Feedback> mList = new ArrayList<>();

    private FeedbackAdapter feedbackAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_syadmin_feedback,null);
        //初始化
        findView(view);
        return view;
    }

    private void findView(View view){
        mListView = view.findViewById(R.id.fSystem_feedback_listview);

        //查询数据库内数据
        BmobQuery<Feedback> query = new BmobQuery<>();
        query.order("-updatedAt");//升序查询
        query.findObjects(new FindListener<Feedback>() {
            @Override
            public void done(List<Feedback> list, BmobException e) {
                if (e == null){
                    mList = list;
                    feedbackAdapter = new FeedbackAdapter(getActivity(),mList);
                    mListView.setAdapter(feedbackAdapter);
                }else {
                    Toast.makeText(getActivity(), "反馈信息加载失败" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void toShow(String s){
        Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
    }
}
