package com.liandongfenqi.tongniu.tabfragmenteveryadd;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by tongniu on 2017/8/11.
 */

public class Fragment2 extends LazyLoadFragment {
    @Bind(R.id.textview)
    TextView textview;

    @Override
    protected int setContentView() {
        return R.layout.fragment;
    }
    public static String[] urls = new String[]{//750x500
            "http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://s16.mogucdn.com/p2/170206/upload_1759d25k9a3djeb125a5bcg0c43eg_750x500.jpg"
    };
    public static String[] titles = new String[]{
            "每周7件Tee不重样",
            "俏皮又知性 适合上班族的漂亮衬衫",
            "名侦探柯南",
            "境界之轮回",
            "我的英雄学院",
            "全职猎人",
    };
    @Override
    protected void lazyLoad() {
        List<String> list = new ArrayList<>();
        List<String> titlelist = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {

            list.add(urls[i]);
            titlelist.add(titles[i]);
        }
        textview.setText("bbbbbbbbb");
        String message = "Fragment2" + (isInit ? "已经初始并已经显示给用户可以加载数据" : "没有初始化不能加载数据") + ">>>>>>>>>>>>>>>>>>>";
        showToast(message);
        Log.d(TAG, message);
        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
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

            }
        });
        //设置图片集合
        banner.setImages(list);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.BackgroundToForeground);
        //设置标题集合（当banner样式有显示title时）
       // banner.setBannerTitles(titlelist);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置指示器位置（当banner模式中有指示器时）
       // banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
 /*   public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            *//**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             *//*

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

            //Picasso 加载图片简单用法
         //   Picasso.with(context).load(path).into(imageView);

            //用fresco加载图片简单用法，记得要写下面的createImageView方法
            Uri uri = Uri.parse((String) path);
            imageView.setImageURI(uri);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
            return simpleDraweeView;
        }
    }*/
    @Override
    protected void stopLoad() {
        Log.d(TAG, "Fragment2" + "已经对用户不可见，可以停止加载数据");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
