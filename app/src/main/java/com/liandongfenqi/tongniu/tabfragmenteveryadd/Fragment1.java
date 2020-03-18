package com.liandongfenqi.tongniu.tabfragmenteveryadd;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import ezy.ui.view.BannerView;

/**
 * Created by tongniu on 2017/8/11.
 */

public class Fragment1 extends LazyLoadFragment  {


    @BindView(R.id.iamge)
    ImageView iamge;
    @BindView(R.id.scrollView)
    MyScrollView scrollView;
    @BindView(R.id.parent_layout)
    LinearLayout parentLayout;
    @BindView(R.id.buy)
    LinearLayout mBuyLayout;
    @BindView(R.id.top_buy_layout)
    LinearLayout mTopBuyLayout;
    @BindView(R.id.banner1)
    BannerView banner1;
/*    private val imageUrls = arrayOf("http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg")*/
    @Override
    protected int setContentView() {
        return R.layout.activity_scrollview;
    }
    public static String[] titles = new String[]{
            "每周7件Tee不重样",
            "俏皮又知性 适合上班族的漂亮衬衫",
            "名侦探柯南",
            "境界之轮回",
            "我的英雄学院",
            "全职猎人",
    };
    public static String[] urls = new String[]{//750x500
            "http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg",
            "http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg",
            "http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg",
            "http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg",
            "http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg",
            "http://s18.mogucdn.com/p2/170122/upload_66g1g3h491bj9kfb6ggd3i1j4c7be_750x500.jpg"
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
    protected void lazyLoad() {
        //当布局的状态或者控件的可见性发生改变回调的接口
        List<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.image = urls[i];
            item.title = titles[i];

            list.add(item);
        }
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
        mTopBuyLayout.setVisibility(View.GONE);
        parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                scrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
                    @Override
                    public void onScroll(int scrollY) {
                        mTopBuyLayout.setVisibility(View.VISIBLE);
                        Log.i("aaaaa","  scrollY"+scrollY+"      mBuyLayout.getTop()"+mBuyLayout.getTop()+"");
                        int mBuyLayout2ParentTop = Math.max(scrollY, mBuyLayout.getTop());

                        Log.i("aaaaa","Top"+mBuyLayout2ParentTop+"       Left "+
                                mTopBuyLayout.getWidth()+"        bottom"+(mBuyLayout2ParentTop + mTopBuyLayout.getHeight()));
                        mTopBuyLayout.layout(0, mBuyLayout2ParentTop, mTopBuyLayout.getWidth(), mBuyLayout2ParentTop + mTopBuyLayout.getHeight());
                    }
                });

            }
        });
    }

    @Override
    protected void stopLoad() {
        Log.d(TAG, "Fragment1" + "已经对用户不可见，可以停止加载数据");
    }

}
