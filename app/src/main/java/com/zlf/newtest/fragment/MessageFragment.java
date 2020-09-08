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

import com.zlf.newtest.DetailActivity.MessageDetail;
import com.zlf.newtest.R;
import com.zlf.newtest.adapter.MessageAdapter;
import com.zlf.newtest.entity.Message;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.fragment
 * 文件名：    ButlerFragment
 * 创建者：    ZLF
 * 创建时间:   2020/5/5 0005 19:05
 * 描述：      景区资讯页面
 */
public class MessageFragment extends Fragment {

    //声明list
    private ListView mListView;
    private List<Message> mList = new ArrayList<>();

    private MessageAdapter messageAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        mListView = (ListView) view.findViewById(R.id.listView_message);

        //查询数据库内的数据
        BmobQuery<Message> query = new BmobQuery<>();
        query.order("updatedAt");//升序查询
        query.findObjects(new FindListener<Message>() {
            @Override
            public void done(List<Message> list, BmobException e) {
                if (e == null) {

                   // Toast.makeText(getActivity(), "成功,共" + list.size() + "条数据", Toast.LENGTH_LONG).show();
                    mList = list;
                    messageAdapter = new MessageAdapter(getActivity(), mList);
                    mListView.setAdapter(messageAdapter);
                } else {
                    Toast.makeText(getActivity(), "资讯加载失败" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        //设置mListView点击事件实现页面跳转
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MessageDetail.class);
                Message message = new Message();
                message = mList.get(position);
                // 实例化一个Bundle
                Bundle bundle = new Bundle();
                bundle.putSerializable("messageEntity",message);
                intent.putExtras(bundle);
                startActivity(intent);
              //  Toast.makeText(getActivity(), "跳转到资讯详情", Toast.LENGTH_LONG).show();

            }
        });
    }
}
