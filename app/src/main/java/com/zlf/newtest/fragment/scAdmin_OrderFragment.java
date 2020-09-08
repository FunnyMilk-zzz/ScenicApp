package com.zlf.newtest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zlf.newtest.R;
import com.zlf.newtest.adapter.UserOrderAdapter;
import com.zlf.newtest.entity.Order;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 项目名：    NewTest
 * 包名：      com.zlf.newtest.fragment
 * 文件名：    scAdmin_OrderFragment
 * 创建者：    ZLF
 * 创建时间:   2020/5/9 0009 1:31
 * 描述：      TODO
 */
public class scAdmin_OrderFragment extends Fragment {
    //声明list
    private ListView mListView;
    private List<Order> mList = new ArrayList<>();
    private UserOrderAdapter userOrderAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scadmin_order,null);
        findView(view);
        return view;
    }
    private void findView(View view) {
        mListView = view.findViewById(R.id.listView_scadmin_order);
        //获取订单信息
        getOrder();
    }

    private void getOrder() {
//        //获取订单类型为：预约导游的订单
//        BmobQuery<Order> eq1 = new BmobQuery<Order>();
//        eq1.addWhereEqualTo("kinds", "预约导游");
//        //条件2：时间升序查询订单
//        BmobQuery<Order> eq2 = new BmobQuery<Order>();
//        eq2.order("-updatedAt");
//        //合并条件
//        List<BmobQuery<Order>> andQuerys = new ArrayList<BmobQuery<Order>>();
//        andQuerys.add(eq1);
//        andQuerys.add(eq2);

        //查询
        BmobQuery<Order> query = new BmobQuery<Order>();
        query.order("-updateAt");
        query.findObjects(new FindListener<Order>() {
            @Override
            public void done(List<Order> list, BmobException e) {
                if (e == null) {
                    for (int i = list.size() - 1; i >= 0; i--) {
                        Order o = list.get(i);
                        mList.add(o);
                        userOrderAdapter = new UserOrderAdapter(getActivity(), mList);
                        mListView.setAdapter(userOrderAdapter);
                    }
                } else {
                    tostShow("查询订单错误" + e.toString());
                }
            }
        });
    }

    private void tostShow(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
    }
}
