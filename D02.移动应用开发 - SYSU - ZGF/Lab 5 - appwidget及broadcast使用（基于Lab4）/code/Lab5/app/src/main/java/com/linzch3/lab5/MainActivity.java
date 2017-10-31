package com.linzch3.lab5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;


public class MainActivity extends AppCompatActivity {

    /*商品列表相关*/
    private List<Product> mProductList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    /*购物车列表相关*/
    private List<Map<String, Object>> mShoppingCartList = new ArrayList<>();
    private ListView mListView;
    /*悬浮动作按钮相关*/
    private boolean isShoppingCartView;//标记当前是否是购物车界面
    FloatingActionButton fab;
    /*StartAppReceiver相关（保存MainActivity的context供其调用getResources函数）*/
    public static Context mContext;
    public static final String STARTAPPSIGNAL="com.linzch3.lab5.StartAppSingal";

    /*本地广播相关*/
    private LocalReceiver mLocalReceiver;
    private IntentFilter mIntentFilter;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /***************初始化***************/
         /*商品列表相关*/
        initProducts();//初始化商品列表数据
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
         /*购物车列表相关*/
        initShoppingCart();//初始化购物车
        mListView = (ListView) findViewById(R.id.shopping_cart_list_view);
         /*悬浮动作按钮相关*/
        fab = (FloatingActionButton) findViewById(R.id.floating_action_bar);
        /*设置当前应该显示的视图*/
        checkOpenShoppingCart();//检查是否有intent要启用购物车列表
        setCurrentView(); //根据isShoppingCartView设置当前的视图
        /*StartAppReceiver相关*/
        mContext = this.getBaseContext();
        /*本地广播相关*/
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(Main2Activity.PRODUCT_ON_SHOPPING_CART);//只接受下单信息
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, mIntentFilter);//注册广播接收器
        /***************初始化***************/

        /*StartAppReceiver相关*/
        randomRecommendProduct();

        /*商品列表相关*/
        final ProductAdapter mProductAdapter = new ProductAdapter(mProductList);
        ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(mProductAdapter);
        animationAdapter.setDuration(1000);
        animationAdapter.setInterpolator(new OvershootInterpolator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(animationAdapter);

        mProductAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                /*点击跳转*/
                //Toast.makeText(MainActivity.this, "点击功能测试正常", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                Product product = mProductList.get(position);
                bundle.putString("产品名称", product.getName());
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onLongClick(int position) {
                /*长按删除*/
                mProductAdapter.removeItem(position);
               Toast.makeText(MainActivity.this, "移除第"+position+"个商品", Toast.LENGTH_SHORT).show();
            }
        });

        /*购物车列表相关*/
        final SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, mShoppingCartList, R.layout.shopping_cart_item,
                new String[] {"first_letter","name", "price"},
                new int[] {R.id.product_name_firstLetter2, R.id.product_name2, R.id.product_price2});
        mListView.setAdapter(mSimpleAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*点击跳转*/
                if(position!=0){
                    //Toast.makeText(MainActivity.this, "点击功能测试正常", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    Bundle bundle = new Bundle();
                    String productName = mShoppingCartList.get(position).get("name").toString();
                    bundle.putString("产品名称", productName);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "长按事件测试正常", Toast.LENGTH_SHORT).show();
                String productName = mShoppingCartList.get(position).get("name").toString();
                final int pos = position;
                if(pos!=0){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("移除商品")
                            .setMessage("从购物车移除"+productName+"?")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mShoppingCartList.remove(pos);
                                    mSimpleAdapter.notifyDataSetChanged();
                                }
                            })
                            .create().show();
                }
                return true;
            }
        });

        /*悬浮动作按钮相关*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "点击fab", Toast.LENGTH_SHORT).show();
                setCurrentView();
            }
        });

    }
    /*重载如下函数以解决launchMode为singleTask或singleInstance时无法获取新的intent的问题*/
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        //here we can use getIntent() to get the extra data.
        checkOpenShoppingCart();
        setCurrentView();
    }

    void checkOpenShoppingCart(){
        /*检查是否有intent要启用购物车列表*/
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("open shopping cart")){
            isShoppingCartView = true;
        }else{
            isShoppingCartView = false;
        }
    }

    void initProducts(){
        /*初始化商品列表数据*/
        String [] names = new String[] {"Enchated Forest", "Arla Milk", "Devondale Milk",
                                        "Kindle Oasis", "waitrose 早餐麦片", "Mcvitie's 饼干",
                                        "Ferrero Rocher", "Maltesers", "Lindt", "Borggreve"};
        String [] prices = new String[] {"¥ 5.00", "¥ 59.00", "¥ 79.00", "¥ 2399.00",
                "¥ 179.00", "¥ 14.90", "¥ 132.59", "¥ 141.43",
                "¥ 139.43", "¥ 28.90"};

        int [] imagesIds = new int[] {R.drawable.enchated_forest_pic, R.drawable.arla_milk_pic,
                R.drawable.devondale_milk_pic, R.drawable.kindle_oasis_pic,
                R.drawable.waitrose_pic, R.drawable.mcvitie_pic,
                R.drawable.ferrero_pic, R.drawable.maltesers_pic,
                R.drawable.lindt_pic,R.drawable.borggreve_pic};

        for(int i=0; i < names.length; i++){
            Product product = new Product(names[i], prices[i], imagesIds[i]);
            mProductList.add(product);
        }
    }
    void initShoppingCart(){
        /*初始化购物车*/
        Map<String, Object> temp = new LinkedHashMap<>();
        temp.put("first_letter", "*");
        temp.put("name", "购物车");
        temp.put("price", "价格");
        mShoppingCartList.add(temp);
    }

    void setCurrentView(){
        /*设置当前的视图，在recyclerView和listView之间转换*/
        if(isShoppingCartView){
                    /*处于购物车视图*/
            fab.setImageResource(R.drawable.mainpage_icon);
            isShoppingCartView = false;
            mRecyclerView.setVisibility(View.INVISIBLE);
            mListView.setVisibility(View.VISIBLE);

        }else{
                    /*处于主界面视图*/
            fab.setImageResource(R.drawable.buy_icon);
            isShoppingCartView = true;
            mRecyclerView.setVisibility(View.VISIBLE);
            mListView.setVisibility(View.INVISIBLE);
        }
    }
    void randomRecommendProduct(){
        /*随机推荐一个商品*/
        Random random = new Random();
        Product luckyOne =  mProductList.get(random.nextInt(mProductList.size()));//选择商品

        Intent mStartAppIntent = new Intent(STARTAPPSIGNAL);
        Bundle mStartAppBundle = new Bundle();
        mStartAppBundle.putString("产品名称", luckyOne.getName());
        mStartAppBundle.putString("产品价格", luckyOne.getPrice());
        mStartAppBundle.putInt("产品图片", luckyOne.getImageId());
        mStartAppIntent.putExtras(mStartAppBundle);
        sendBroadcast(mStartAppIntent);
    }

    /***************本地广播相关*******************/
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
        mAppWidget.unregisterWidgetBroadcast(); //注销widget的广播
    }

    private class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Toast.makeText(MainActivity.this, "数据接收功能测试正常", Toast.LENGTH_SHORT).show();
            Bundle bundle = intent.getExtras();
            String targetProductName = bundle.getString("产品名称");
            String targetProductPrice = bundle.getString("产品价格");
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("first_letter", String.valueOf(targetProductName.charAt(0)).toUpperCase());
            temp.put("name", targetProductName);
            temp.put("price", targetProductPrice);
            mShoppingCartList.add(temp);
        }
    }
    /***************本地广播相关*******************/
}
