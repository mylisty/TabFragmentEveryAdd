package gov.anzong.lunzi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-08-10 16:44<br>
 * //   这玩意的用处：<br>
 */
public class DisScrollViewPager extends ViewPager {
    public DisScrollViewPager(Context context) {
        super(context);
    }

    public DisScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //去除页面切换时的滑动翻页效果

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

    private boolean result = false;//禁用滑动

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return result && super.onInterceptTouchEvent(arg0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return result && super.onTouchEvent(arg0);
    }
}
