package com.zlf.newtest.DetailActivity.Pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.R;
import com.zlf.newtest.entity.Guide;
import com.zlf.newtest.entity.MyUser;
import com.zlf.newtest.entity.Order;
import com.zlf.newtest.entity.Ticket;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.DetailActivity.Pay
 * 文件名：    PayTicket
 * 创建者：    ZLF
 * 创建时间:   2020/5/30 0030 21:14
 * 描述：      购买门票
 */
public class PayTicket extends Activity  implements View.OnClickListener  {
    //声明浮动窗口
    private Window win;

    //声明
    private ListView mListView;
    private Ticket ticket;
    private List<Ticket> mList = new ArrayList<>();

    //声明控件
    private TextView tv_duty;
    private TextView tv_price;
    private TextView tv_cancel;
    private TextView tv_pay;

    //声明日期选择器相关控件
    private DatePicker datePicker ;
    private int mYear,mMonth,mDay;
    private String dateNow;

    //声明订单类
    Order order = new Order();

    //判断接受的item种类以及相关
    String duty;
    String price;
    String itemKind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay_ticket);
        initWindow();
        initView();
    }



    private void initView(){
        getBundle();//取传过来的对象

        tv_duty = findViewById(R.id.dialog_pay_ticket_duty);
        tv_price = findViewById(R.id.dialog_pay_ticket_price);
        tv_pay = findViewById(R.id.dialog_pay_ticket_submit);
        tv_cancel = findViewById(R.id.dialog_pay_ticket_cancel);
        tv_cancel.setOnClickListener(this);

        //获取日期
        getDate();

        //判断传输过来的门票类型（itemA or B）根据类型填写相应的内容
        if (itemKind.equals("A")){
     //       toastShow("A");
            duty = ticket.getItemA();
            price = ticket.getaPrice()+"";
            tv_duty.setText(ticket.getTicketName()+" "+duty);
            tv_price.setText(price+"/张");
        }else if (itemKind.equals("B")){
            duty = ticket.getItemB();
            price = ticket.getbPrice()+"";
            tv_duty.setText(ticket.getTicketName()+" "+duty);
            tv_price.setText(price+"/张");
     //       toastShow("B");
        }else {
            toastShow("门票信息获取错误");
        }

        //pay点击事件
        tv_pay.setOnClickListener(this);

    }

    //获取GuideDetail中传过来的Guide对象
    private void getBundle(){
        Intent intent = getIntent();
        //实例化Bundle
        Bundle bundle = intent.getExtras();
        ticket = (Ticket) bundle.getSerializable("Ticket");
        itemKind = (String) bundle.getSerializable("itemKind");
    }


    //获取日期方法
    private void getDate(){
        datePicker = findViewById(R.id.dialog_pay_ticket_dp);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);
        long l = 2592000000L;
        datePicker.setMaxDate(System.currentTimeMillis()+ l);
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        dateNow = mYear+"年"+(mMonth+1)+"月"+mDay+"日";
        datePicker.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                PayTicket.this.mYear = year;
                PayTicket.this.mMonth = monthOfYear;
                PayTicket.this.mDay = dayOfMonth;
                dateNow = mYear+"年"+(mMonth+1)+"月"+mDay+"日";
            }
        });
    }
    private void initWindow(){
        //设置弹出窗口与屏幕对齐
        win = this.getWindow();
        int density = (int)(getResources().getDisplayMetrics().density + 0.5f);
        //设置内边距，这里设置为10dp
        win.getDecorView().setPadding(10 * density, 10 * density, 10 * density, 10 * density);
        WindowManager.LayoutParams lp = win.getAttributes();
        //设置窗口宽度
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //设置窗口高度
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置Dialog位置
        lp.gravity = Gravity.BOTTOM;
        win.setAttributes(lp);
    }

    public void finish(){
        //转场动画设置
        super.finish();
        overridePendingTransition(R.anim.pop_in,R.anim.pop_out);
    }

    private void toastShow(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_pay_ticket_cancel:
                toastShow("取消支付");
                finish();
                break;

            case R.id.dialog_pay_ticket_submit:
                //生成订单
                createOrder();
                finish();
                break;
        }
    }

    private void createOrder(){
        order = new Order();
        //获取下单用户信息（通过缓存）
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);

        //生成订单信息时间已经提前获取
        String ticketId = ticket.getObjectId();
        String userId = myUser.getUsername();
        Boolean payIs = true;

        //判断门票类型
        if (itemKind.equals("A")){
            String sum = ticket.getaPrice()+"";

            //将门票项目A类信息放入订单
            order.setGoodsId(ticketId);
            order.setUserId(userId);
            order.setBookDate(dateNow);
            order.setPayIs(payIs);
            order.setSum(sum);
            order.setKinds("景区门票");

            //push到数据库
            order.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if(e == null){
                        toastShow("订单生成成功");
                    }else {
                        toastShow("支付失败"+e.toString());
                    }
                }
            });
        }else if(itemKind.equals("B")){
            String sum = ticket.getbPrice()+"";

            //将门票项目A类信息放入订单
            order.setGoodsId(ticketId);
            order.setUserId(userId);
            order.setBookDate(dateNow);
            order.setPayIs(payIs);
            order.setSum(sum);
            order.setKinds("景区门票");

            //push到数据库
            order.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if(e == null){
                        toastShow("订单生成成功");
                    }else {
                        toastShow("支付失败"+e.toString());
                    }
                }
            });
        }else {
            toastShow("订单生成错误");
        }
    }
}
