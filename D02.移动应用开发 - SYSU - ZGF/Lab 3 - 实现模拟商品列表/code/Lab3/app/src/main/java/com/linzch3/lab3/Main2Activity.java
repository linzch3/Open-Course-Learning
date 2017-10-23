package com.linzch3.lab3;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    /*商品详细信息存储与读取相关*/
    private Map<String, ProductDetail> mProductDetailsMap = new HashMap<>();
    /*本地广播相关*/
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
        /*默认下一次收藏按钮应是实心的*/
        isFullStar = true;
        /***************初始化***************/

        /*根据intent传递的数据加载对应商品的详细数据*/
        Bundle bundle = getIntent().getExtras();
        String targetProductName = bundle.getString("产品名称");
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
                Intent intent = new Intent("com.linzch3.lab3.LOCAL_BROADCAST");
                Bundle bundle = new Bundle();
                bundle.putString("产品名称", mProductName.getText().toString());
                bundle.putString("产品价格", mProductPrice.getText().toString());
                intent.putExtras(bundle);
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        });
        /* //#############debug code###################//
        Log.i("原来的名字：",mProductName.getText().toString());
        Log.i("intent获取到的名字：", bundle.getString("名称"));
           //#############debug code###################// */
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
}
