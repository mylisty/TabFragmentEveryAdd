package gov.anzong.lunzi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2016-12-05 11:19<br>
 * //   这玩意的用处：立即刷新的下拉刷新组件<br>
 */
public class RefreshImmediatelySwipeToRefreshLayout extends SwipeRefreshLayout {

    private boolean mMeasured = false;
    private boolean mPreMeasureRefreshing = false;
    private boolean mAcceptEvents;

    public RefreshImmediatelySwipeToRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshImmediatelySwipeToRefreshLayout(Context context) {
        super(context);
    }

    public void setAcceptEvents(boolean mAcceptEvents) {
        this.mAcceptEvents = mAcceptEvents;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !mAcceptEvents || super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAcceptEvents = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAcceptEvents = false;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!mMeasured) {
            mMeasured = true;
            setRefreshing(mPreMeasureRefreshing);
        }
    }


    @Override
    public void setRefreshing(boolean refreshing) {
        if (mMeasured) {
            super.setRefreshing(refreshing);
        } else {
            mPreMeasureRefreshing = refreshing;
        }
    }
}
