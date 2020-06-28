package com.liandongfenqi.tongniu.tabfragmenteveryadd.ui.adapter;

import android.content.Context;
import android.util.Log;

import com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter.BaseExpandableAdapter;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter.BaseExpandableDataBean;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter.BasicExpandableChildViewHolder2;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter.BasicExpandableGroupViewHolder2;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.ui.holder.ScanHistoryDetailChildViewHolder;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.ui.holder.ScanHistoryDetailGroupViewHolder;

import java.util.List;

import gov.anzong.lunzi.util.AnzongLogUtils;

public class HistoryAdapter extends BaseExpandableAdapter<String,String> {
    public HistoryAdapter(List<BaseExpandableDataBean<String, String>> dataList, Context context) {
        super(dataList,context);
        AnzongLogUtils.e("aaaaaaaaa2 "+ (context == null));
    }
    @Override
    public BasicExpandableGroupViewHolder2<String, String> initGroupHolder() {
        return new ScanHistoryDetailGroupViewHolder(this,context);
    }

    @Override
    public BasicExpandableChildViewHolder2<String, String> initChildHolder() {
        return new ScanHistoryDetailChildViewHolder(this,context);
    }
}
