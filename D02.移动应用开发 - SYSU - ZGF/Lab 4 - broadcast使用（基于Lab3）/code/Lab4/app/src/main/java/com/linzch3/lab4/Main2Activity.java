package com.linzch3.lab4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.linzch3.lab4.MainActivity.mContext;

public class Main2Activity extends AppCompatActivity {

    /*商品详细信息存储与读取相关*/
    private Map<String, ProductDetail> mProductDetailsMap = new HashMap<>();
    /*本地广播相关*/
    private static final String PRODUCT_ON_SHOPPING_CART = "com.linzch3.lab4.LOCAL_BROADCAST";
    private LocalReceiver mLocalReceiver;
    private IntentFilter mIntentFilter;
    private LocalBroadcastManager mLocalBroadcastManager;
    /*标记收藏按钮下一次是否是实心的*/
    private boolean isFullStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /***************初始化***************/
        /*商品详细信息存储与读取相关*/
        initProductDetails();//初始化所有产品的详细信息
        /*布局元素相关*/
        ListView mlistView = (ListView) findViewById(R.id.list_view);
        ImageView mProductImage = (ImageView) findViewById(R.id.product_image);
        final TextView mProductName = (TextView) findViewById(R.id.product_name);
        TextView mProductType = (TextView) findViewById(R.id.type);
        TextView mProductInfo = (TextView) findViewById(R.id.type_info);
        final TextView mProductPrice = (TextView) findViewById(R.id.product_price);
        ImageView mBackIcon = (ImageView) findViewById(R.id.back_icon);
        final ImageView mStarIcon = (ImageView) findViewById(R.id.star_icon);
        ImageButton mBuyIcon = (ImageButton) findViewById(R.id.buy_icon);
        /*本地广播相关*/
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(PRODUCT_ON_SHOPPING_CART);
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, mIntentFilter);
        /*默认下一次收藏按钮应是实心的*/
        isFullStar = true;
        /***************初始化***************/

        /*根据intent传递的数据加载对应商品的详细数据*/
        Bundle bundle = getIntent().getExtras();
        String targetProductName = bundle.getString("产品名称");
        Log.i("产品名字", targetProductName);
        ProductDetail productDetail = mProductDetailsMap.get(targetProductName);
        mProductName.setText(targetProductName);
        mProductPrice.setText(productDetail.getPrice());
        mProductType.setText(productDetail.getType());
        mProductInfo.setText(productDetail.getInfo());
        mProductImage.setImageResource(productDetail.getImageId());
        /*根据intent传递的数据加载对应商品的详细数据*/

        /*界面下面列表*/
        String [] otherActionData = new String[] {"一键下单","分享商品","不敢兴趣","查看更多商品信息",""};
        mlistView.setAdapter(new ArrayAdapter<>(
                Main2Activity.this, R.layout.other_action_item, otherActionData
        ));

        /*返回按钮*/
        mBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /*收藏按钮*/
        mStarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFullStar){
                    mStarIcon.setImageResource(R.drawable.full_star_icon);
                    isFullStar = false;
                }else{
                    mStarIcon.setImageResource(R.drawable.empty_star_icon);
                    isFullStar = true;
                }
            }
        });
        /*加入购物车按钮*/
        mBuyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "商品已添加到购物车", Toast.LENGTH_SHORT).show();

                String productName = mProductName.getText().toString();
                String productPrice = mProductPrice.getText().toString();
                /*EventBus发布事件部分*/
                EventBus.getDefault().post(new PlaceOrderEvent(productName, productPrice));
                /*动态广播触发notification部分*/
                Intent intent = new Intent(PRODUCT_ON_SHOPPING_CART);
                Bundle bundle = new Bundle();
                bundle.putString("产品名称",productName);
                intent.putExtras(bundle);
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        });
        /* //#############debug code###################//
        Log.i("原来的名字：",mProductName.getText().toString());
        Log.i("intent获取到的名字：", bundle.getString("名称"));
           //#############debug code###################// */
    }
    /*本地广播相关*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    void initProductDetails(){
        /*初始化所有产品的详细信息*/
        String [] names = new String[] {"Enchated Forest", "Arla Milk", "Devondale Milk",
                "Kindle Oasis", "waitrose 早餐麦片", "Mcvitie's 饼干",
                "Ferrero Rocher", "Maltesers", "Lindt", "Borggreve"};
        String [] prices = new String[] {"¥ 5.00", "¥ 59.00", "¥ 79.00", "¥ 2399.00",
                "¥ 179.00", "¥ 14.90", "¥ 132.59", "¥ 141.43",
                "¥ 139.43", "¥ 28.90"};
        String [] types = new String[] {"作者", "产地", "产地", "版本", "重量", "产地", "重量",
                "重量", "重量", "重量"};
        String [] infos = new String[] {"Johanna Basford", "德国", "澳大利亚", "8GB", "2Kg",
                "英国", "300g", "118g", "249g", "640g"};
        int [] imagesIds = new int[] {R.drawable.enchated_forest_pic, R.drawable.arla_milk_pic,
                R.drawable.devondale_milk_pic, R.drawable.kindle_oasis_pic,
                R.drawable.waitrose_pic, R.drawable.mcvitie_pic,
                R.drawable.ferrero_pic, R.drawable.maltesers_pic,
                R.drawable.lindt_pic,R.drawable.borggreve_pic};

        for(int i=0; i < names.length; i++){
            ProductDetail productDetail = new ProductDetail(prices[i], types[i], infos[i], imagesIds[i]);
            mProductDetailsMap.put(names[i], productDetail);
        }
    }
    /*本地广播相关*/
    private class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Toast.makeText(Main2Activity.this, "数据接收功能测试正常", Toast.LENGTH_SHORT).show();
            Bundle bundle = intent.getExtras();
            String productName = bundle.getString("产品名称");

            /*准备要跳转的数据*/
            Intent intent2ShoppingCart = new Intent(context, MainActivity.class);
            Bundle bundle2ShoppingCart = new Bundle();
            bundle2ShoppingCart.putBoolean("open shopping cart", true);
            intent2ShoppingCart.putExtras(bundle2ShoppingCart);

            //注意将flag设置为 PendingIntent.FLAG_UPDATE_CURRENT
            PendingIntent pi = PendingIntent.getActivity(context, 0, intent2ShoppingCart, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager notificationManager = (NotificationManager)
                    context.getSystemService(NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat.Builder(context)
                    .setTicker("您有一条新信息")
                    .setContentTitle("马上下单")
                    .setContentText(productName+"已添加到购物车")
                    .setSmallIcon(R.drawable.buy_icon)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.buy_icon))
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pi)
                    .setAutoCancel(true)
                    .build();
            int uniqueId = (int) System.currentTimeMillis();//根据时间来为每次id的显示指定不同的id
            notificationManager.notify(uniqueId, notification);
        }
    }
}
