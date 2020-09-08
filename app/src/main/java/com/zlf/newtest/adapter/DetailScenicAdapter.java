package com.zlf.newtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Message;
import com.zlf.newtest.entity.Scenic;

import java.util.List;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.adapter
 * 文件名：    DetailScenicAdapter
 * 创建者：    ZLF
 * 创建时间:   2020/5/8 0008 22:24
 * 描述：      TODO
 */
public class DetailScenicAdapter extends BaseAdapter{
    //常用变量
    private Context context;
    private List<Scenic> list;

    //构造方法
    public DetailScenicAdapter(Context context, List<Scenic> list){
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
            String name = list.get(position).getScenicName();
            String introduce = list.get(position).getIntroduce();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_scenic_detail,null);

            TextView tv_name = convertView.findViewById(R.id.item_Scenic_detail_tv_name);
            TextView tv_introduce = convertView.findViewById(R.id.item_Scenic_detail_tv_introduce);

            tv_name.setText(name);
            tv_introduce.setText(introduce);
        }



        return convertView;
    }
}
