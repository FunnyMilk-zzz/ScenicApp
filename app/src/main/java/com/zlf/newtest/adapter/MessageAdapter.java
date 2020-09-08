package com.zlf.newtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Message;

import java.util.List;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.adapter
 * 文件名：    MessageAdapter
 * 创建者：    ZLF
 * 创建时间:   2020/5/7 0007 18:54
 * 描述：      资讯适配器
 */
public class MessageAdapter extends BaseAdapter {

    //常用变量
    private Context context;
    private List<Message> list;

    //构造方法
    public MessageAdapter(Context context , List<Message>list){
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
            String title = list.get(position).getMessageTitle();
            String author = list.get(position).getAuthor();
            String introduce = list.get(position).getIntroduce();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_message,null);

            TextView tv_title = convertView.findViewById(R.id.item_Message_title);
            TextView tv_author = convertView.findViewById(R.id.item_Message_author);
            TextView tv_introduce = convertView.findViewById(R.id.item_Message_introduce);

            tv_title.setText(title);
            tv_author.setText(author);
            tv_introduce.setText(introduce);
        }
        return convertView;
    }

    //常用变量

}
