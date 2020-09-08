package com.zlf.newtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Guide;

import java.util.List;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.adapter
 * 文件名：    DetailGuideAdapter
 * 创建者：    ZLF
 * 创建时间:   2020/5/8 0008 23:04
 * 描述：      TODO
 */
public class DetailGuideAdapter extends BaseAdapter {

    //常用变量
    private Context context;
    private List<Guide> list;

    //构造方法
    public DetailGuideAdapter(Context context,List<Guide> list){
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
            String introduce = list.get(position).getIntroduce();
            Integer price = list.get(position).getPrice();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_guide_detail,null);

            //放数据
            TextView tv_name =convertView.findViewById(R.id.item_Guide_detail_tv_name);
            TextView tv_introduce =convertView.findViewById(R.id.item_Guide_detail_tv_introduce);
            TextView tv_city =convertView.findViewById(R.id.item_Guide_detail_tv_city);
            TextView tv_age =convertView.findViewById(R.id.item_Guide_detail_tv_age);
            TextView tv_worktime =convertView.findViewById(R.id.item_Guide_detail_tv_worktime);
            TextView tv_hobby =convertView.findViewById(R.id.item_Guide_detail_tv_hobby);
            TextView tv_duty =convertView.findViewById(R.id.item_Guide_detail_tv_duty);
            TextView tv_price =convertView.findViewById(R.id.item_Guide_detail_tv_price);

            tv_name.setText(name);
            tv_introduce.setText(introduce);
            tv_city.setText("所在城市："+city);
            tv_age.setText("年龄："+age+"岁");
            tv_worktime.setText("带团时间："+worktime);
            tv_hobby.setText("我的专长："+hobby);
            tv_duty.setText("导游业务："+duty);
            tv_price.setText("￥"+price+"/天");
        }


        return convertView;
    }
}
