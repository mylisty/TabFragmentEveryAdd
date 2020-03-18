package com.liandongfenqi.tongniu.tabfragmenteveryadd;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.MyScrollView.OnScrollListener;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.utils.StatusBarCompat;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.view.SlideLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ezy.ui.view.BannerView;

/**
 * Created by tongniu on 2017/8/18.
 */

public class ScrollViewMainActivity extends AppCompatActivity implements OnScrollListener {
    @BindView(R.id.banner1)
    BannerView banner1;
    @BindView(R.id.iamge)
    ImageView iamge;
    @BindView(R.id.scrollView)
    MyScrollView scrollView;
    @BindView(R.id.parent_layout)
    LinearLayout parentLayout;
    /* * 自定义的MyScrollView
     */
    private MyScrollView myScrollView;
    /**
     * 在MyScrollView里面的购买布局
     */
    private LinearLayout mBuyLayout;
    /**
     * 位于顶部的购买布局
     */
    private LinearLayout mTopBuyLayout;
    public static String[] urls = new String[]{//750x500
            "http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://s16.mogucdn.com/p2/170206/upload_1759d25k9a3djeb125a5bcg0c43eg_750x500.jpg"
    };

    public static class BannerItem {
        public String image;
        public String title;

        @Override
        public String toString() {
            return title;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        ButterKnife.bind(this);
        StatusBarCompat.compat(this);
        myScrollView = (MyScrollView) findViewById(R.id.scrollView);
        mBuyLayout = (LinearLayout) findViewById(R.id.buy);
        mTopBuyLayout = (LinearLayout) findViewById(R.id.top_buy_layout);
        //当布局的状态或者控件的可见性发生改变回调的接口
        List<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.image = urls[i];

            list.add(item);
        }
        if (enableSliding()) {
            SlideLayout rootView = new SlideLayout(this);
            rootView.bindActivity(this);
        }
        myScrollView.setOnScrollListener(this);

        //当布局的状态或者控件的可见性发生改变回调的接口
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(myScrollView.getScrollY());

            }
        });
        banner1.setViewFactory(new BannerView.ViewFactory<BannerItem>() {
            @Override
            public View create(BannerItem bannerItem, int position, ViewGroup container) {
                ImageView iv = new ImageView(container.getContext());

                /* Glide.with(container.getContext().getApplicationContext()).load(bannerItem.image).into(iv);*/
                Glide.with(container.getContext().getApplicationContext())
                        .load(bannerItem.image)
                        .into(iv);
                return iv;
            }
        });
        banner1.setDataList(list);
        banner1.start();
    }

    protected boolean enableSliding() {
        return true;
    }

    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, mBuyLayout.getTop());
        mTopBuyLayout.layout(0, mBuyLayout2ParentTop, mTopBuyLayout.getWidth(), mBuyLayout2ParentTop + mTopBuyLayout.getHeight());
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }
}
