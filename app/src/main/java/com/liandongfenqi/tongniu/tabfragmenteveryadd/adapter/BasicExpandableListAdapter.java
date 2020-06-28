package com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;


import java.util.ArrayList;
import java.util.List;


/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-07-07 12:40<br>
 * //   这玩意的用处：<br>
 */
public abstract class BasicExpandableListAdapter<G, C, V extends FinalBaseView> extends BaseExpandableListAdapter {

    public V mIBaseView;
    public List<BaseExpandableDataBean<G, C>> dataList;

    public BasicExpandableListAdapter(List<BaseExpandableDataBean<G, C>> dataList, V baseView) {
        super();
        this.dataList = dataList == null ? new ArrayList<>() : dataList;
        this.mIBaseView = baseView;
    }

    @Override
    public int getGroupCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (dataList == null || dataList.size() <= groupPosition) {
            return 0;
        }
        return dataList.get(groupPosition).childList == null ? 0 : dataList.get(groupPosition).childList.size();
    }

    @Override
    public G getGroup(int groupPosition) {
        return dataList.get(groupPosition).groupObject;
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        return dataList.get(groupPosition).childList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        BasicExpandableGroupViewHolder holder;
        if (convertView == null) {
            holder = initGroupHolder();
            convertView = holder.holderView;
            convertView.setTag(holder);
        } else {
            holder = (BasicExpandableGroupViewHolder) convertView.getTag();
        }
        holder.bindGroupData(groupPosition,isExpanded);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        BasicExpandableChildViewHolder holder;
        if (convertView == null) {
            holder = initChildHolder();
            convertView = holder.holderView;
            convertView.setTag(holder);
        } else {
            holder = (BasicExpandableChildViewHolder) convertView.getTag();
        }
        holder.bindChildData(groupPosition, childPosition);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public abstract BasicExpandableGroupViewHolder<G, C, V> initGroupHolder();

    public abstract BasicExpandableChildViewHolder<G, C, V> initChildHolder();
}
