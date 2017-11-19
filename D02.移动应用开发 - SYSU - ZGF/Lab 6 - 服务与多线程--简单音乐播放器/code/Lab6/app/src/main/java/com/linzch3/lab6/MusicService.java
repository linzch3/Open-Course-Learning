package com.linzch3.lab6;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import java.io.IOException;

public class MusicService extends Service {
    /*MediaPlyaer相关*/
    public final static int PLAY_MUSIC = 1;
    public final static int STOP_MUSIC = 2;
    public final static int QUIT_APP = 3;
    public final static int REFRESH_UI = 4;
    public final static int DRAG_SEEKBAR = 5;
    public final static int INIT_MEDIAPLAYER = 6;
    /*Bundle发送数据相关*/
    public final static String KEY_CURRENTTIME = "currentTime";
    public final static String KEY_DURATION = "duration";

    private IBinder mBinder;
    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("debug", "MusicService创建");
        /*初始化*/
        mMediaPlayer = new MediaPlayer();
        mBinder = new MusicBinder();
        /*初始化*/
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MusicBinder extends Binder{
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code)
            {
                case PLAY_MUSIC:
                    /*播放音乐*/
                    if(mMediaPlayer!=null){
                        if(mMediaPlayer.isPlaying()){
                            mMediaPlayer.pause();
                        }else{
                            mMediaPlayer.start();
                        }
                    }
                    break;
                case STOP_MUSIC:
                    /*停止音乐*/
                    /*代码应这样写才可以在下次播放时重新开始播放 */
                    if(mMediaPlayer!=null){
                        mMediaPlayer.stop();
                        try {
                            mMediaPlayer.prepare();
                            mMediaPlayer.seekTo(0);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case QUIT_APP:
                    /*退出应用*/
                    if(mMediaPlayer!=null){
                        mMediaPlayer.reset();
                        mMediaPlayer.release();
                    }
                    break;
                case REFRESH_UI:
                    /*更新UI界面*/
                    if(mMediaPlayer!=null){
                        Bundle replayData = new Bundle();
                        try {
                            replayData.putInt(KEY_CURRENTTIME, mMediaPlayer.getCurrentPosition());
                            replayData.putInt(KEY_DURATION, mMediaPlayer.getDuration());
                            reply.writeBundle(replayData);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                    break;
                case DRAG_SEEKBAR:
                    /*拖动进度条*/
                    if(mMediaPlayer!=null){
                        int currentPosition = data.readInt();
                        mMediaPlayer.seekTo(currentPosition);
                    }
                    break;
                case INIT_MEDIAPLAYER:
                    /*初始化MediapPlyaer*/
                    try {
                        String filePath = Environment.getExternalStorageDirectory()+"/melt.mp3";
                        mMediaPlayer.setDataSource(filePath);
                        Log.e("debug", "音乐文件路径："+filePath);
                        mMediaPlayer.prepare();
                        mMediaPlayer.setLooping(true);
                        Log.e("debug", "初始化mediaplayer成功");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    Log.e("debug", "in MusicBinder onTransact() 未定义switch case条件分支："+code);
                    break;
            }
            return super.onTransact(code, data, reply, flags);
        }
    }
}
