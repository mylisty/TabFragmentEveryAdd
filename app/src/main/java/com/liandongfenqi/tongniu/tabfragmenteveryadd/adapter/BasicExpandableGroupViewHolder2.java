package com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter;

import android.content.Context;
import android.view.View;

import gov.anzong.lunzi.util.AnzongLogUtils;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-07-03 13:53<br>
 * //   这玩意的用处：<br>
 */
public abstract class BasicExpandableGroupViewHolder2<G, C> {
    public View holderView;
    protected BaseExpandableAdapter<G, C> adapter;
    public Context context;
    public BasicExpandableGroupViewHolder2(BaseExpandableAdapter<G, C> adapter, Context context) {
        this.adapter = adapter;
        this.context = context;
        this.holderView = getInflateView();
        AnzongLogUtils.e("aaaaaaaaa3 "+ (context == null));
    }

    protected G getGroup(int groupPosition) {
        return adapter.getGroup(groupPosition);
    }

    public abstract View getInflateView();

    public abstract void bindGroupData(int groupPosition,boolean isExpanded);


}
