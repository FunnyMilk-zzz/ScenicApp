package com.zlf.newtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Guide;
import com.zlf.newtest.entity.Scenic;

import java.util.List;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.adapter
 * 文件名：    GuideAdapter
 * 创建者：    ZLF
 * 创建时间:   2020/5/8 0008 1:57
 * 描述：      TODO
 */
public class GuideAdapter extends BaseAdapter {

    //常用变量
    private Context context;
    private List<Guide> list;

    //构造方法
    public GuideAdapter(Context context,List<Guide> list){
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
            String name = list.get(position).getName();
            String duty =  list.get(position).getDuty();
            String city  = list.get(position).getCity();
            Integer age = list.get(position).getAge();
            String worktime = list.get(position).getWorkTime();
            String hobby = list.get(position).getHobby();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_guide,null);

            //放数据
            TextView tv_name = convertView.findViewById(R.id.item_guide_tv_name);
            TextView tv_duty = convertView.findViewById(R.id.item_guide_tv_duty);
            TextView tv_city = convertView.findViewById(R.id.item_guide_tv_city);
            TextView tv_age = convertView.findViewById(R.id.item_guide_tv_age);
            TextView tv_worktime = convertView.findViewById(R.id.item_guide_tv_worktime);
            TextView tv_hobby= convertView.findViewById(R.id.item_guide_tv_hobby);

            tv_name.setText(name);
            tv_duty.setText(duty);
            tv_city.setText("所在城市："+city);
            tv_age.setText("年龄："+age);
            tv_worktime.setText("带团时间："+worktime);
            tv_hobby.setText("我的专长："+hobby);

        }



        return convertView;
    }
}
