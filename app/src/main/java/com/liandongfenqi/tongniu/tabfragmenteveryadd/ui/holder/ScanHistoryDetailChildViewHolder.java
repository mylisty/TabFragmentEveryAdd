package com.liandongfenqi.tongniu.tabfragmenteveryadd.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.liandongfenqi.tongniu.tabfragmenteveryadd.R;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter.BaseExpandableAdapter;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter.BasicExpandableChildViewHolder2;

import butterknife.BindView;
import butterknife.ButterKnife;
import gov.anzong.lunzi.util.AnzongStringUtils;

public class ScanHistoryDetailChildViewHolder extends BasicExpandableChildViewHolder2<String,String> {

    @BindView(R.id.child_text)
    TextView childTv;

    public ScanHistoryDetailChildViewHolder(BaseExpandableAdapter<String, String> adapter, Context context) {
        super(adapter, context);
    }
    @Override
    public View getInflateView() {
        View rootView = View.inflate(context, R.layout.item_scan_history_detail_child, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void bindChildData(int groupPosition, int childPosition) {
        childTv.setText(AnzongStringUtils.nullStrToLine(getChild(groupPosition, childPosition)));
    }
}
