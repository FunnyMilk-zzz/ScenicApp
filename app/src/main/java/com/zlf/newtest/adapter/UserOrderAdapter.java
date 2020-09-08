package com.zlf.newtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Order;
import com.zlf.newtest.entity.Scenic;

import java.util.List;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.adapter
 * 文件名：    UserOrderAdapter
 * 创建者：    ZLF
 * 创建时间:   2020/5/10 0010 21:17
 * 描述：      TODO
 */
public class UserOrderAdapter extends BaseAdapter {
    //常用变量
    private Context context;
    private List<Order> list;

    //构造方法
    public UserOrderAdapter(Context context, List<Order> list) {
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
        if (convertView == null) {
            String orderName = list.get(position).getKinds();
            String userId=list.get(position).getUserId();
            String orderDate = list.get(position).getCreatedAt();
            String bookDate = list.get(position).getBookDate();
            String orderId = list.get(position).getObjectId();
            String payIs = list.get(position).getPayIs()+"";
            String price = list.get(position).getSum();

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_order_uesr,null);

            //放数据
            TextView tv_orderName = convertView.findViewById(R.id.item_Order_user_name);
            TextView tv_userId = convertView.findViewById(R.id.item_Order_user_Username);
            TextView tv_orderDate = convertView.findViewById(R.id.item_Order_user_orderDate);
            TextView tv_bookDate = convertView.findViewById(R.id.item_Order_user_bookDate);
            TextView tv_orderId = convertView.findViewById(R.id.item_Order_user_orderId);
            TextView tv_payIs = convertView.findViewById(R.id.item_Order_user_payIs);
            TextView tv_price = convertView.findViewById(R.id.item_Order_user_price);

            tv_orderName.setText("订单类型："+ orderName);
            tv_userId.setText("下单人"+userId);
            tv_orderDate.setText("下单日期："+orderDate);
            tv_bookDate.setText("预定日期："+bookDate);
            tv_orderId.setText("订单编号："+orderId);
            tv_payIs.setText("支付状态"+payIs);
            tv_price.setText("价格:"+price+"元");


        }

        return convertView;
    }
}
