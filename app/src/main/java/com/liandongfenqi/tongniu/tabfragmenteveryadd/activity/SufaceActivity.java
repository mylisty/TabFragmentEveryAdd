package com.liandongfenqi.tongniu.tabfragmenteveryadd.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ViewFlipper;

import com.liandongfenqi.tongniu.tabfragmenteveryadd.R;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;


/**
 *  Created by tongniu on 2017/8/22.
*/

public class SufaceActivity extends AppCompatActivity {
    private IjkMediaPlayer ijkMediaPlayer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suface);
       ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper);
        flipper.startFlipping();//开始切换，注意，如果设置了时间间隔，想让它自动切换，一定要记得加它
        /*videoView = (IjkVideoView) findViewById(R.id.video_view);
        videoView.setAspectRatio(IRenderView.AR_ASPECT_FIT_PARENT);
        videoView.setVideoURI(Uri.parse("http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8"));
        videoView.start();*/
    }
}
