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
import com.zlf.newtest.adapter.MessageAdapter;
import com.zlf.newtest.entity.Message;
import com.zlf.newtest.ui.adminUI.PublishMessageActivity;
import com.zlf.newtest.ui.adminUI.PublishSerivceActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.fragment
 * 文件名：    scAdmin_MessageFragment
 * 创建者：    ZLF
 * 创建时间:   2020/5/9 0009 1:30
 * 描述：      TODO
 */
public class scAdmin_MessageFragment extends Fragment implements View.OnClickListener {
    //声明list
    private ListView mListView;
    private List<Message> mList = new ArrayList<>();
    private MessageAdapter messageAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scadmin_message,null);
        findView(view);
        return view;
    }
    private void findView(View view) {
        Button pubBtn =view.findViewById(R.id.fScAdmin_message_btn_pub_msg);
        pubBtn.setOnClickListener(this);
        Button btn_pubService = view.findViewById(R.id.fScAdmin_message_btn_pub_service);
        btn_pubService.setOnClickListener(this);

        mListView = (ListView) view.findViewById(R.id.fScAdmin_message_listView);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fScAdmin_message_btn_pub_msg:
                startActivity(new Intent(getActivity(), PublishMessageActivity.class));
                break;

            case R.id.fScAdmin_message_btn_pub_service:
                startActivity(new Intent(getActivity(), PublishSerivceActivity.class));
                break;
        }
    }
}
