package com.linzch3.lab6;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;


public class MainActivity extends AppCompatActivity {
    /*UI组件相关*/
    private Button playButton;
    private Button stopButton;
    private Button quitButton;
    private TextView currentProgress;
    private TextView duration;
    private TextView musicStatus;
    private ImageView musicImage;
    private SeekBar seekBar;
    /*当前音乐状态相关*/
    public final static String STATUS_DEFAULT = "";
    public final static String STATUS_PLAYING = "Playing";
    public final static String STATUS_PAUSED = "Paused";
    public final static String STATUS_STOPPED = "Stopped";
    /*图片旋转相关*/
    private ObjectAnimator mMusicImageAnimator;
    /*后台服务相关*/
    private IBinder mMusicBinder;
    private ServiceConnection mMusicServiceConnection;

    final boolean[] initMeidaPlayerSuccessed = {false};
    /*多线程更新UI相关*/
    public final static int UPDATE_UI = 1;
    private Handler mUiUpdateHandler;
    /*获取Sd卡权限相关*/
    private static boolean SdcardReadable = false;//标记当前SD卡是否可读

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*获取Sd卡权限相关*/
        verifyStoragePermissions(this);

        /*UI组件相关*/
        playButton = (Button)findViewById(R.id.play_button);
        stopButton = (Button)findViewById(R.id.stop_button);
        quitButton = (Button)findViewById(R.id.quit_button);
        currentProgress = (TextView)findViewById(R.id.current_progress);
        duration = (TextView)findViewById(R.id.max_progress);
        musicStatus = (TextView)findViewById(R.id.current_status);
        musicStatus.setText(STATUS_DEFAULT);
        musicImage = (ImageView)findViewById(R.id.music_image);
        seekBar = (SeekBar)findViewById(R.id.seekbar);
        /*图片旋转相关*/
        mMusicImageAnimator = ObjectAnimator.ofFloat(musicImage, "rotation", 0, 360);//0到360旋转
        mMusicImageAnimator.setInterpolator(new LinearInterpolator());//线性变化
        mMusicImageAnimator.setRepeatCount(ValueAnimator.INFINITE);//无穷次重复
        mMusicImageAnimator.setRepeatMode(ValueAnimator.RESTART);//每个周期结束后从头开始
        mMusicImageAnimator.setDuration(12000);//单个周期的时间(ms)
        /*后台服务相关*/
        mMusicServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mMusicBinder = service;
                /*当SD卡可读且未初始化过播放器时，初始化播发器*/
                if(SdcardReadable && !initMeidaPlayerSuccessed[0])
                {
                    initMediaPlayer();
                    initMeidaPlayerSuccessed[0] = true;
                }
                Log.e("debug", "服务连接成功");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mMusicBinder = null;
                Log.e("debug", "服务意外断开！（客户端解除绑定服务时，该方法不会被调用）");
            }
        };
        Intent bindIntent = new Intent(this, MusicService.class);
        startService(bindIntent);//开启服务
        bindService(bindIntent, mMusicServiceConnection, BIND_AUTO_CREATE);//绑定服务

         /*多线程更新UI相关*/
         mUiUpdateHandler = new Handler(){
             @Override
             public void handleMessage(Message msg) {
                 super.handleMessage(msg);
                 switch (msg.what)
                 {
                     case UPDATE_UI:
                         /*更新UI*/
                         SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");//设置音乐时间的格式
                         Parcel inputData = Parcel.obtain();
                         Parcel expectedReply = Parcel.obtain();
                         try {
                             mMusicBinder.transact(MusicService.REFRESH_UI, inputData, expectedReply, 0);
                             Bundle replyData = expectedReply.readBundle();
                             /*更新播放时间的最大值*/
                             if(replyData.containsKey(MusicService.KEY_DURATION))
                             {
                                 duration.setText(timeFormat.format(Integer.valueOf(replyData.getInt(MusicService.KEY_DURATION))));
                                 seekBar.setMax(replyData.getInt(MusicService.KEY_DURATION));
                             }
                             /*更新当前已播放时间*/
                             if(replyData.containsKey(MusicService.KEY_CURRENTTIME)){
                                 currentProgress.setText(timeFormat.format(Integer.valueOf(replyData.getInt(MusicService.KEY_CURRENTTIME))));
                                 seekBar.setProgress(replyData.getInt(MusicService.KEY_CURRENTTIME));
                             }
                         } catch (RemoteException e) {
                             e.printStackTrace();
                         }
                         break;
                     default:
                         Log.e("debug", "in handleMessage() 未定义switch case条件分支："+msg.what);
                         break;
                 }
             }
         };
         new Thread(){
             @Override
             public void run() {
                 super.run();
                 /*每100ms更新一次UI*/
                 while (true)
                 {
                     try {
                         Thread.sleep(100);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                     /*当服务还处于绑定状态并且已经初始化播放器时更新UI*/
                     if(mMusicServiceConnection != null && initMeidaPlayerSuccessed[0]){
                         mUiUpdateHandler.obtainMessage(UPDATE_UI).sendToTarget();
                     }
                 }
             }
         }.start();

        /*PLAY按钮相关*/
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*根据当前的音乐状态设置play按钮的文字信息和图片的动画效果*/
                if(musicStatus.getText().equals(STATUS_DEFAULT)){
                    /*若是默认状态，则状态改变为Playing，play按钮文字改变为PAUSED*/
                    musicStatus.setText(STATUS_PLAYING);
                    playButton.setText("PAUSED");
                    if(!mMusicImageAnimator.isStarted()){
                        mMusicImageAnimator.start();
                    }
                    else if(mMusicImageAnimator.isPaused()){
                        mMusicImageAnimator.resume();
                    }else{
                        Log.e("debug", "unexpected mMusicImageAnimator state");
                    }
                }
                else if(musicStatus.getText().equals(STATUS_PLAYING)){
                    /*若是Playing状态，则状态改变为Pause，play按钮文字改变为PLAY*/
                    musicStatus.setText(STATUS_PAUSED);
                    playButton.setText("PLAY");
                    mMusicImageAnimator.pause();
                }else{
                    /*若是其他状态，则状态改变为Playing，play按钮文字改变为PAUSED*/
                    musicStatus.setText(STATUS_PLAYING);
                    playButton.setText("PAUSED");
                    if(!mMusicImageAnimator.isStarted()){
                        mMusicImageAnimator.start();
                    }
                    else if(mMusicImageAnimator.isPaused()){
                        mMusicImageAnimator.resume();
                    }else{
                        Log.e("debug", "unexpected mMusicImageAnimator state");
                    }
                }
                /*发送点击了play按钮的信息到后台服务*/
                Parcel inputData = Parcel.obtain();
                Parcel expectedReply = Parcel.obtain();
                try {
                    mMusicBinder.transact(MusicService.PLAY_MUSIC, inputData, expectedReply, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        /*STOP按钮相关*/
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*设置当前状态、并设置图片回归到原始状态*/
                musicStatus.setText(STATUS_STOPPED);
                playButton.setText("PLAY");
                mMusicImageAnimator.end();
                /*发送点击了stop按钮的信息到后台服务*/
                Parcel inputData = Parcel.obtain();
                Parcel expectedReply = Parcel.obtain();
                try {
                    mMusicBinder.transact(MusicService.STOP_MUSIC, inputData, expectedReply, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        /*QUIT按钮相关*/
        final Intent stopIntent = new Intent(this, MusicService.class);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*发送点击了quit按钮的信息到后台服务，提示后台服务释放资源*/
                Parcel inputData = Parcel.obtain();
                Parcel expectedReply = Parcel.obtain();
                try {
                    mMusicBinder.transact(MusicService.QUIT_APP, inputData, expectedReply, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                /*取消绑定服务并停止服务*/
                unbindService(mMusicServiceConnection);
                mMusicServiceConnection = null;
                stopService(stopIntent);
                /*关闭当前的Activity以及所有相关的线程*/
                finish();
                System.exit(0);
            }
        });
        /*SeekBar相关*/
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                /*发送拖动了seekbar的信息到后台服务*/
                Parcel inputData = Parcel.obtain();
                inputData.writeInt(seekBar.getProgress());
                Parcel expectedReply = Parcel.obtain();
                try {
                    mMusicBinder.transact(MusicService.DRAG_SEEKBAR, inputData, expectedReply, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*获取Sd卡权限相关*/
    public void verifyStoragePermissions(Activity activity)
    {
        try {
            if (ActivityCompat.checkSelfPermission(activity, "android.permission.READ_EXTERNAL_STORAGE") != 0)
            {
                Log.e("debug", "此处应弹出提示框");
                ActivityCompat.requestPermissions(activity,
                        new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
            }else
            {
                Log.e("debug", "成功获取权限 in verifyStoragePermissions()");
                SdcardReadable = true;
            }
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        Log.e("debug", "verifyStoragePermissions()执行完成");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 1:
                Log.e("debug", "系统回调，尝试获取权限");
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    /*若用户同意app获取权限，标记可读取文件*/
                    Log.e("debug", "成功获取权限 in onRequestPermissionsResult()");
                    SdcardReadable = true;
                }else{
                    /*若用户拒绝app获取权限，则弹出信息提示“拒绝权限将无法使用本程序”*/
                    Toast.makeText(this, "拒绝权限将无法使用本程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                Log.e("debug", "undefined requestCode:"+requestCode);
                break;
        }
    }
    /*获取Sd卡权限相关*/

    void initMediaPlayer()
    {
        /*初始化MediaPlayer*/
        Parcel inputData = Parcel.obtain();
        Parcel expectedReply = Parcel.obtain();
        try {
            mMusicBinder.transact(MusicService.INIT_MEDIAPLAYER, inputData, expectedReply, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
