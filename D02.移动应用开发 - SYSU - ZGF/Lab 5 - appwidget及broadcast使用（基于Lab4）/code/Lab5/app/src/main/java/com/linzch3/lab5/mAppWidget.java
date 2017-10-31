package com.linzch3.lab5;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static com.linzch3.lab5.MainActivity.STARTAPPSIGNAL;
import static com.linzch3.lab5.MainActivity.mContext;

/**
 * Implementation of App Widget functionality.
 */
public class mAppWidget extends AppWidgetProvider {

    /**********************设置初始界面*********************/
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        setDefaultWidget(context, appWidgetManager);/*设置默认的widget*/
    }

    public void setDefaultWidget(Context context, AppWidgetManager appWidgetManager){
        /*设置默认的widget*/
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.m_app_widget);
        updateViews.setOnClickPendingIntent(R.id.appwidegt_img, pi);
        ComponentName componentName = new ComponentName(context, mAppWidget.class);
        appWidgetManager.updateAppWidget(componentName, updateViews);
    }
    /**********************设置初始界面*********************/

    /************************静态广播相关******************************/
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(STARTAPPSIGNAL)) {
            /**************app启动后显示随机推荐*****/

            registerWidgetBroadcast(context);//注册widget的动态广播

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

            /*更新widget的图片和文字信息*/
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.m_app_widget);
            updateViews.setTextViewText(R.id.appwidget_text, productName+"仅售"+productPrice+"!");
            updateViews.setImageViewResource(R.id.appwidegt_img, productImageId);
            updateViews.setOnClickPendingIntent(R.id.appwidegt_img, pi);
            ComponentName componentName = new ComponentName(context, mAppWidget.class);
            appWidgetManager.updateAppWidget(componentName, updateViews);
        }
    }
    /************************静态广播相关******************************/

    /***********************本地广播相关**********************************/
    private static LocalReceiver mLocalReceiver;
    private IntentFilter mIntentFilter;
    private static LocalBroadcastManager mLocalBroadcastManager;

    private void registerWidgetBroadcast(Context context){
        /*注册widget的广播*/
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
            mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Main2Activity.PRODUCT_ON_SHOPPING_CART);
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, mIntentFilter);//注册广播接收器
    }

    public static void unregisterWidgetBroadcast(){
        /*注销widget的广播*/
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    private class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(mContext, "数据接收功能测试正常", Toast.LENGTH_SHORT).show();
            /*得到广播传送过来的数据*/
            Bundle bundle = intent.getExtras();
            String productName = bundle.getString("产品名称");
            int productImageId = bundle.getInt("产品图片");

            /*准备要跳转的数据*/
            Intent intent2ShoppingCart = new Intent(context, MainActivity.class);
            Bundle bundle2ShoppingCart = new Bundle();
            bundle2ShoppingCart.putBoolean("open shopping cart", true);
            intent2ShoppingCart.putExtras(bundle2ShoppingCart);

            //注意将flag设置为 PendingIntent.FLAG_UPDATE_CURRENT
            PendingIntent pi = PendingIntent.getActivity(context, 0, intent2ShoppingCart, PendingIntent.FLAG_UPDATE_CURRENT);

            /*更新widget的图片和文字信息*/
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.m_app_widget);
            updateViews.setTextViewText(R.id.appwidget_text, productName+"已添加到购物车");
            updateViews.setImageViewResource(R.id.appwidegt_img, productImageId);
            updateViews.setOnClickPendingIntent(R.id.appwidegt_img, pi);
            ComponentName componentName = new ComponentName(context, mAppWidget.class);
            appWidgetManager.updateAppWidget(componentName, updateViews);
        }
    }
    /***********************本地广播相关**********************************/
}

