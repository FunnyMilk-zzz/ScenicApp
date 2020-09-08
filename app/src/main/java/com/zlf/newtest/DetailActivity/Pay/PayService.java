package com.zlf.newtest.DetailActivity.Pay;

import android.app.Activity;
import android.app.Service;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.DetailActivity.Pay
 * 文件名：    PayService
 * 创建者：    ZLF
 * 创建时间:   2020/6/3 0003 19:39
 * 描述：      支付游玩服务
 */
public class PayService  extends Activity implements View.OnClickListener {
    //声明浮动窗口
    private Window win;

    //声明
    private ListView mListView;
    private com.zlf.newtest.entity.Service service;
    private List<Service> mList = new ArrayList<>();

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay_service);
        initWindow();
        initView();
    }

    private void initView(){
        getBundle();//取传过来的对象
        tv_duty = findViewById(R.id.dialog_pay_service_duty);
        tv_price = findViewById(R.id.dialog_pay_service_price);
        tv_pay = findViewById(R.id.dialog_pay_service_submit);
        tv_cancel = findViewById(R.id.dialog_pay_service_cancel);
        tv_cancel.setOnClickListener(this);


        //获取内容
        String duty = service.getServiceName();
        String price = service.getPrice();
        tv_duty.setText(duty);
        tv_price.setText(price+"元");

        //获取日期
        getDate();

        //pay点击
        tv_pay.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dialog_pay_service_cancel:
                toastShow("取消支付");
                break;

            case R.id.dialog_pay_service_submit:
                //生成订单类
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
        String goodsId = service.getObjectId();
        String userId = myUser.getUsername();
        Boolean payIs = true;
        String sum = service.getPrice();

        //放入订单类
        order.setGoodsId(goodsId);
        order.setUserId(userId);
        order.setBookDate(dateNow);
        order.setPayIs(payIs);
        order.setSum(sum);
        order.setKinds("吃喝玩乐");

        //push到数据库
        order.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    toastShow("订单创建成功");
                }else {
                    toastShow("支付失败"+e.toString());
                }
            }
        });

    }

    //获取日期方法
    private void getDate(){
        datePicker = findViewById(R.id.dialog_pay_service_dp);
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
                PayService.this.mYear = year;
                PayService.this.mMonth = monthOfYear;
                PayService.this.mDay = dayOfMonth;
                dateNow = mYear+"年"+(mMonth+1)+"月"+mDay+"日";
            }
        });

    }
    //获取Service对象
    private void getBundle(){
        Intent intent = getIntent();
        //实例化Bundle
        Bundle bundle = intent.getExtras();
        service = (com.zlf.newtest.entity.Service) bundle.getSerializable("Service");
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
}
