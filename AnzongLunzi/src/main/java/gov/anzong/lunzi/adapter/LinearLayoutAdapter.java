package gov.anzong.lunzi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import gov.anzong.lunzi.view.AddViewsLinearLayout;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-07-09 20:27<br>
 * //   这玩意的用处：<br>
 */
public class LinearLayoutAdapter {
    private Context mContext;
    private AddViewsLinearLayout mLinearLayout;
    private ListAdapter mListAdapter;
    private boolean showDivider;
    private int dividerHeight;

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
    }

    private int dividerColor;

    public LinearLayoutAdapter(Context c, AddViewsLinearLayout layout, ListAdapter adapter) {
        super();
        this.mContext = c;
        this.mLinearLayout = layout;
        this.mListAdapter = adapter;
    }

    public void setShowDivider(boolean showDivider) {
        this.showDivider = showDivider;
    }

    public void setDividerHeight(int dividerHeight) {
        this.dividerHeight = dividerHeight;
    }

    public void initView() {
        if (mContext == null || mLinearLayout == null || mListAdapter == null) {
            return;
        }
        mLinearLayout.removeAllViews();
        if (mListAdapter.getCount() < 1) {
            return;
        }
        List<View> views = new ArrayList<>();
        for (int i = 0; i < mListAdapter.getCount(); i++) {
            views.add(mListAdapter.getView(i, null, mLinearLayout));
            if (!showDivider || dividerHeight <= 0F) {
                continue;
            }
            if (i < mListAdapter.getCount() - 1) {
                View v = new View(mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.height = dividerHeight;
                v.setBackgroundColor(dividerColor);
                v.setLayoutParams(layoutParams);
                views.add(v);
            }
        }
        mLinearLayout.addViews(views);
    }
}
