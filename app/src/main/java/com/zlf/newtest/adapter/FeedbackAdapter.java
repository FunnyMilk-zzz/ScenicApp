package com.zlf.newtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Feedback;

import java.util.List;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.adapter
 * 文件名：    FeedbackAdapter
 * 创建者：    ZLF
 * 创建时间:   2020/5/30 0030 20:37
 * 描述：      反馈适配器
 */
public class FeedbackAdapter extends BaseAdapter {
    //常用变量
    private Context context;
    private List<Feedback> list;

    //构造方法
    public FeedbackAdapter(Context context , List<Feedback> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            String title = list.get(position).getTitle();
            String body = list.get(position).getBody();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_feedback,null);

            TextView tv_title = convertView.findViewById(R.id.item_feedback_title);
            TextView tv_body = convertView.findViewById(R.id.item_feedback_body);

            tv_title.setText(title);
            tv_body.setText(body);

        }
        return convertView;
    }
}
