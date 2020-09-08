package com.zlf.newtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Message;

import java.util.List;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.adapter
 * 文件名：    DetailMessageAdapter
 * 创建者：    ZLF
 * 创建时间:   2020/5/8 0008 19:58
 * 描述：      资讯详情适配器
 */
public class DetailMessageAdapter extends BaseAdapter {
    //常用变量
    private Context context;
    private List<Message> list;

    //构造方法
    public DetailMessageAdapter(Context context,List<Message> list){
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
            String author = list.get(position).getAuthor();
            String body = list.get(position).getMessageBody();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_message_detail,null);

            TextView tv_author = convertView.findViewById(R.id.item_Message_detail_author);
            TextView tv_body = convertView.findViewById(R.id.item_Message_detail_MessageBody);

            tv_author.setText(author);
            tv_body.setText(body);
        }


        return convertView;
    }
}
