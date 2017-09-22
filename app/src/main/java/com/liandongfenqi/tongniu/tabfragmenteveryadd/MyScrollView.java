package com.liandongfenqi.tongniu.tabfragmenteveryadd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by tongniu on 2017/8/18.
 */

public class MyScrollView extends ScrollView {
    private OnScrollListener onScrollListener;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 设置滚动接口
     * @param onScrollListener
     */
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }


    @Override
    public int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListener != null){
            onScrollListener.onScroll(t);
        }
    }



    /**
     *
     * 滚动的回调接口
     *
     * @author xiaanming
     *
     */
    public interface OnScrollListener{
        /**
         * 回调方法， 返回MyScrollView滑动的Y方向距离
         * @param scrollY
         *              、
         */
        public void onScroll(int scrollY);
    }
    private int startX;
    private int startY;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
       // getParent().requestDisallowInterceptTouchEvent(true);     //请求父控件不拦截事件
        getParent().requestDisallowInterceptTouchEvent(false);
        switch (ev.getAction()) {
            case  MotionEvent.ACTION_DOWN :
                //按下
                startX = (int) ev.getX();
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE :
                //移动
                int endX = (int) ev.getX();
                int endY = (int) ev.getY();

                int dx = endX - startX;
                int dy = endY - startY;
                if (Math.abs(dx) > Math.abs(dy)) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                } else {
                    //上下滑动
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

}
