package com.liandongfenqi.tongniu.tabfragmenteveryadd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.utils.StatusBarCompat;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tongniu on 2017/8/18.
 */

public class SplashActivity extends Activity {
    public static String[] urls = new String[]{//750x500
            "http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://s16.mogucdn.com/p2/170206/upload_1759d25k9a3djeb125a5bcg0c43eg_750x500.jpg"
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
// No Titlebar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        StatusBarCompat.compat(this);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            list.add(urls[i]);
        }
        Banner banner = (Banner) findViewById(R.id.banner);
        //设置banner样式
      //  banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new ImageLoaderInterface() {
            @Override
            public void displayImage(Context context, Object path, View imageView) {
                Glide.with(context).load(path).into((ImageView) imageView);
            }
            @Override
            public View createImageView(Context context) {
                ImageView imageView = new ImageView(context);
                return imageView;
            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
               /* Toast.makeText(activity, "postion"+position, Toast.LENGTH_SHORT).show();*/
                Log.i("aaaaaaaaaaaaa","click"+position);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
        //设置图片集合
        banner.setImages(list);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.BackgroundToForeground);
        //设置标题集合（当banner样式有显示title时）
        // banner.setBannerTitles(titlelist);
        //设置自动轮播，默认为true
        banner.isAutoPlay(false);
//        //设置轮播时间
//        banner.setDelayTime(3000);
        banner.setDelayTime(3000);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（当banner模式中有指示器时）
        // banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }
}
