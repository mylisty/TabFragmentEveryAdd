package gov.anzong.lunzi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-07-09 20:23<br>
 * //   这玩意的用处：<br>
 */
public class AddViewsLinearLayout extends LinearLayout {
    private Context mContext;

    public AddViewsLinearLayout(Context context) {
        super(context);
        this.mContext = context;

    }

    public AddViewsLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        return super.addViewInLayout(child, index, params);
    }

    public void addViews(List<View> views) {
        if (views == null || views.size() == 0) {
            return;
        }
        for (View view : views) {
            addViewInLayout(view, -1, view.getLayoutParams(), true);
        }
        requestLayout();
        invalidate();
    }
}
