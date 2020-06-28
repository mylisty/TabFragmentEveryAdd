package com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

import gov.anzong.lunzi.util.AnzongLogUtils;

public abstract class BaseExpandableAdapter<G,C> extends BaseExpandableListAdapter {
    private static final String TAG = "aaaaaaa";
    // G 第一层的数据bean C 第二次的数据
    public List<BaseExpandableDataBean<G, C>> dataList;
    public Context context;
    public BaseExpandableAdapter(List<BaseExpandableDataBean<G, C>> dataList, Context context) {
        this.dataList = dataList == null? new ArrayList<>():dataList;
        this.context = context;
        AnzongLogUtils.e("aaaaaaaaa1 "+ (context == null));
    }
   // group条数
    @Override
    public int getGroupCount() {
        return dataList == null ? 0 : dataList.size();
    }
    //每个子集的size
    @Override
    public int getChildrenCount(int groupPosition) {
        AnzongLogUtils.e(TAG,"getChildrenCount  groupPosition "+ groupPosition);
        if (dataList == null || dataList.size() <= groupPosition) {
            return 0;
        }
        return dataList.get(groupPosition).childList == null ? 0 : dataList.get(groupPosition).childList.size();
    }
   // 获取每个父级的数据
    @Override
    public G getGroup(int groupPosition) {
        return  dataList.get(groupPosition).groupObject;
    }
  // 每个子集的数据
    @Override
    public C getChild(int groupPosition, int childPosition) {
        return dataList.get(groupPosition).childList.get(childPosition);
    }
   // 子集在父级的位置
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
   // 子集中的位置
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
  // 父级的布局
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        BasicExpandableGroupViewHolder2 holder;
        if (convertView == null) {
            holder = initGroupHolder();
            convertView = holder.holderView;
            convertView.setTag(holder);
        } else {
            holder = (BasicExpandableGroupViewHolder2) convertView.getTag();
        }
        holder.bindGroupData(groupPosition,isExpanded);
        return convertView;
    }
  // 子集的布局
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        BasicExpandableChildViewHolder2 holder;
        if (convertView == null) {
            holder = initChildHolder();
            convertView = holder.holderView;
            convertView.setTag(holder);
        } else {
            holder = (BasicExpandableChildViewHolder2) convertView.getTag();
        }
        holder.bindChildData(groupPosition, childPosition);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    public abstract BasicExpandableGroupViewHolder2<G, C> initGroupHolder();

    public abstract BasicExpandableChildViewHolder2<G, C> initChildHolder();
}
