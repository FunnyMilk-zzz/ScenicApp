package com.zlf.newtest.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Message;
import com.zlf.newtest.entity.Scenic;
import com.zlf.newtest.entity.ScenicAdmin;

import java.util.List;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.adapter
 * 文件名：    ScenicAdapter
 * 创建者：    ZLF
 * 创建时间:   2020/5/8 0008 0:26
 * 描述：      TODO
 */
public class ScenicAdapter extends BaseAdapter {

    //常用变量
    private Context context;
    private List<Scenic> list;

    //构造方法
    public ScenicAdapter(Context context,List<Scenic> list){
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
            String introduce = list.get(position).getIntroduce();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_scenic,null);

            //放数据
            TextView tv_title = convertView.findViewById(R.id.item_Scenic_title);
            TextView tv_introduce = convertView.findViewById(R.id.item_Scenic_introduce);

            tv_title.setText(title);
            tv_introduce.setText(introduce);

        }


        return convertView;
    }
}
