package com.zlf.newtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Scenic;
import com.zlf.newtest.entity.Ticket;

import java.util.List;

import javax.security.auth.callback.Callback;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.adapter
 * 文件名：    ScenicTicketAdapter
 * 创建者：    ZLF
 * 创建时间:   2020/5/10 0010 19:05
 * 描述：      门票展示适配器
 */
public class ScenicTicketAdapter extends BaseAdapter {
    //常用变量
    private Context context;
    private List<Ticket> list;

    //
    private LayoutInflater mInflater;
    private MyClickListener mListener;


    //构造方法打
    public ScenicTicketAdapter(Context context, List<Ticket> list,MyClickListener listener) {
        this.context = context;
        this.list = list;
        mListener = listener;

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

    RecyclerView.ViewHolder viewHolder;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //holder
        ViewHolder holder;

        if (convertView == null) {
            String ticketName = list.get(position).getTicketName();
            String itemA = list.get(position).getItemA();
            String aPirce = list.get(position).getaPrice() + "元";
            String itemB = list.get(position).getItemB();
            String bPrice = list.get(position).getbPrice() + "元";

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_scenic_ticket, null);

            //放数据
            TextView tv_ticketName = convertView.findViewById(R.id.item_Scenic_ticket_name);
            TextView tv_itemA = convertView.findViewById(R.id.item_Scenic_ticket_itemA);
            TextView tv_aPirce = convertView.findViewById(R.id.item_Scenic_ticket_aPrice);
            TextView tv_itemB = convertView.findViewById(R.id.item_Scenic_ticket_itemB);
            TextView tv_bPrice = convertView.findViewById(R.id.item_Scenic_ticket_bPrice);

            tv_ticketName.setText(ticketName);
            tv_itemA.setText(itemA);
            tv_aPirce.setText(aPirce);
            tv_itemB.setText(itemB);
            tv_bPrice.setText(bPrice);

        //按钮点击相关
         holder = new ViewHolder();
         holder.btn_bookA = convertView.findViewById(R.id.item_Scenic_ticket_btnA);
         holder.btn_bookB = convertView.findViewById(R.id.item_Scenic_ticket_btnB);
         convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.btn_bookA.setOnClickListener(mListener);
        holder.btn_bookA.setTag(position);
        holder.btn_bookB.setOnClickListener(mListener);
        holder.btn_bookB.setTag(position);
        return convertView;
    }

    class  ViewHolder{

        Button btn_bookA;
        Button btn_bookB;
    }

    public static abstract class MyClickListener implements View.OnClickListener {
        /**
         * 基类的onClick方法
         */
        @Override
        public void onClick(View v) {
            myOnClick((Integer) v.getTag(), v);
        }
        public abstract void myOnClick(int position, View v);
    }

}
