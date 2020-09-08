//package com.zlf.newtest.utils;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.util.Log;
//
//import com.zlf.newtest.R;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import cn.bmob.push.PushConstants;
//
///**
// * 项目名：    NewTest
// * 包名：      com.zlf.newtest.utils
// * 文件名：    MyPushMessageReceiver
// * 创建者：    ZLF
// * 创建时间:   2020/5/9 0009 3:32
// * 描述：      消息推送
// */
//public class MyPushMessageReceiver extends BroadcastReceiver {
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        // TODO Auto-generated method stub
//        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
//            //收到消息进行一些处理：发送一个通知
//            String jsonStr = intent.getStringExtra(PushConstants.EXTRA_PUSH_MESSAGE_STRING);
//            String content = null;
//            //处理Json的数据，将其转化为正常string类型
//            try {
//                JSONObject jsonObject=new JSONObject(jsonStr);
//                content=jsonObject.getString("alert");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            NotificationManager manager= (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
//            Notification notification= new Notification.Builder(context).
//                    setSmallIcon(R.mipmap.ic_launcher).//设置推送消息的图标
//                    setContentTitle("您收到一条推送信息！") .//设置推送标题
//                    setContentText(content).build();//设置推送内容
//            manager.notify(1,notification);//设置通知栏
//            Log.d("bmob", "客户端收到推送内容："+intent.getStringExtra("msg"));
//        }
//    }
//
//}