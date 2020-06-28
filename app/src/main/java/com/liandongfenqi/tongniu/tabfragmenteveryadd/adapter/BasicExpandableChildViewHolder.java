package com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter;

import android.view.View;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-07-03 13:53<br>
 * //   这玩意的用处：<br>
 */
public abstract class BasicExpandableChildViewHolder<G, C, V extends FinalBaseView> {
    public View holderView;
    protected V mIView;
    protected BasicExpandableListAdapter<G, C, V> adapter;

    public BasicExpandableChildViewHolder(BasicExpandableListAdapter<G, C, V> adapter) {
        this.mIView = adapter.mIBaseView;
        this.adapter = adapter;
        this.holderView = getInflateView();
    }

    protected C getChild(int groupPosition, int childPosition) {
        return adapter.getChild(groupPosition, childPosition);
    }

    protected G getGroup(int groupPosition) {
        return adapter.getGroup(groupPosition);
    }

    protected int getChildrenCount(int groupPosition) {
        return adapter.getChildrenCount(groupPosition);
    }

    public abstract View getInflateView();

    public abstract void bindChildData(int groupPosition, int childPosition);

}
