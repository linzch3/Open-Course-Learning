package com.linzch3.lab5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;
import static com.linzch3.lab5.MainActivity.mContext;


public class StartAppReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //Toast.makeText(context, "数据接收功能测试正常", Toast.LENGTH_SHORT).show();
        /*得到广播传送过来的数据*/
        Bundle bundle = intent.getExtras();
        String productName = bundle.get("产品名称").toString();
        String productPrice = bundle.get("产品价格").toString();
        int productImageId = bundle.getInt("产品图片");
        /*准备要跳转的数据*/
        Intent intent2DetailsPage = new Intent(context, Main2Activity.class);
        Bundle bundle2DetailPage = new Bundle();
        bundle2DetailPage.putString("产品名称", productName);
        intent2DetailsPage.putExtras(bundle2DetailPage);
        //注意将flag设置为 PendingIntent.FLAG_UPDATE_CURRENT
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent2DetailsPage, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager)
                            context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setTicker("您有一条新信息")
                .setContentTitle("新商品热卖")
                .setContentText(productName+"仅售"+productPrice+"!")
                .setSmallIcon(R.drawable.buy_icon)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), productImageId))
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();
        int uniqueId = (int) System.currentTimeMillis();//根据时间来为每次id的显示指定不同的id
        notificationManager.notify(uniqueId, notification);
    }
}
