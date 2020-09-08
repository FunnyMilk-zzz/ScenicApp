package com.zlf.newtest.ui;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlf.newtest.R;
import com.zlf.newtest.adapter.UserOrderAdapter;
import com.zlf.newtest.entity.MyUser;
import com.zlf.newtest.entity.Order;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.ui
 * 文件名：    UserOrderActivity
 * 创建者：    ZLF
 * 创建时间:   2020/5/10 0010 23:14
 * 描述：      游客订单查询
 */
public class UserOrderActivity extends BaseActivity {
    //声明list
    private ListView mListView;
    private List<Order> mList = new ArrayList<>();
    private UserOrderAdapter userOrderAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_user);

        initView();

    }

    private void initView(){
        mListView = findViewById(R.id.listView_order_user);

        //查询该用户的订单信息
        getUserOrder();

    }

    private void getUserOrder(){
        //获取本地用户缓存并取得用户名
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        String userId = user.getUsername();

        //查询条件1：是此时登录的用户
        BmobQuery<Order> eq1 = new BmobQuery<Order>();
        eq1.addWhereEqualTo("userId",userId);
        //查询条件2：时间降序查询订单
        BmobQuery<Order> eq2 = new BmobQuery<Order>();
        eq2.order("-updatedAt");

        //合并条件
        List<BmobQuery<Order>> andQuerys = new ArrayList<BmobQuery<Order>>();
        andQuerys.add(eq1);
        andQuerys.add(eq2);

        //查询
        BmobQuery<Order> query = new BmobQuery<Order>();
        query.and(andQuerys);
        query.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
                if (e == null){
                    for (int i = list.size() - 1; i >= 0; i--) {
                        Order o = list.get(i);
                        mList.add(o);
                        userOrderAdapter = new UserOrderAdapter(UserOrderActivity.this, mList);
                        mListView.setAdapter(userOrderAdapter);
                    }
                }else {
                    tostShow("查询订单错误"+e.toString());
                }
            }
        });

    }
    private void tostShow(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
