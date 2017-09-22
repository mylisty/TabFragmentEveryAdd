package com.liandongfenqi.tongniu.tabfragmenteveryadd.activity;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.liandongfenqi.tongniu.tabfragmenteveryadd.R;

/**
 * Created by tongniu on 2017/8/22.
 */

public class VideoActivity extends Activity implements SurfaceHolder.Callback,View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener {
    private MediaPlayer player;
    private SurfaceView surface;
    private SurfaceHolder surfaceHolder;
    private Button play, pause, stop;
    private SeekBar seekBar;
    private String videoPath;
    private int seekProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initParams();
        initViews();
    }

    private void initParams() {
        videoPath = getIntent().getStringExtra("http://video.jiecao.fm/11/24/6/%E5%AD%94%E6%98%8E%E7%81%AF.mp4");
    }


    private void initViews() {
        play = (Button) findViewById(R.id.btn_play);
        pause = (Button) findViewById(R.id.btn_pause);
        stop = (Button) findViewById(R.id.btn_stop);
        seekBar = (SeekBar) findViewById(R.id.skb_video);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekProgress = progress * player.getDuration()
                        / seekBar.getMax();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekTo(seekProgress);
            }
        });
        surface = (SurfaceView) findViewById(R.id.video_surface);
        surfaceHolder = surface.getHolder();
        surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        surfaceHolder.setKeepScreenOn(true);
        surfaceHolder.addCallback(this);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        autoPlay();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        stop();
        release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop();
        release();
    }

    /**
     * 自动播放
     */
    private void autoPlay() {
        //必须在surface创建后才能初始化MediaPlayer,否则不会显示图像
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //设置显示视频显示在SurfaceView上
        player.setDisplay(surfaceHolder);
        try {
            player.setDataSource("http://video.jiecao.fm/11/24/6/%E5%AD%94%E6%98%8E%E7%81%AF.mp4");
            player.setOnPreparedListener(this);
            player.setOnCompletionListener(this);
            player.setOnBufferingUpdateListener(this);
            player.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 开始播放
     */
    private void start() {
        if (player == null || !player.isPlaying()) {
            player.start();
        }
    }

    /**
     * 暂停播放
     */
    private boolean flag;
    private void pause() {
     /*   if (player == null || player.isPlaying()) {
            return;
        }*/
        if (player!=null&&player.isPlaying()){
            player.pause();
            flag=!flag;
        }

        if (flag){
            player.start();
        }

    }

    /**
     * 停止播放
     */
    private void stop() {
       /* if (player == null || player.isPlaying()) {
            return;
        }*/
        if (player!=null&&player.isPlaying()){
            player.stop();
        }


    }

    /**
     * 释放资源
     */
    private void release() {
    /*    if (player == null) {
            return;
        }*/
    if (player!=null){

        player.release();
    }
    }

    /**
     * 重置播放器
     */
    private void reset() {
        if (player == null) {
            return;
        }
        player.reset();
    }

    /**
     * 指定位置播放
     *
     * @param pos
     */
    private void seekTo(int pos) {
        if (player == null) {
            return;
        }
        player.seekTo(pos);
        start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                start();
                break;
            case R.id.btn_pause:
                pause();
                break;
            case R.id.btn_stop:
                stop();
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        start();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        seekBar.setSecondaryProgress(percent);
        int currentProgress = seekBar.getMax() * player.getCurrentPosition() / player.getDuration();
        Log.e("currentProgress", "currentProgress----->" + currentProgress);

        seekBar.setProgress(currentProgress);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        seekBar.setProgress(0);
    }
}
