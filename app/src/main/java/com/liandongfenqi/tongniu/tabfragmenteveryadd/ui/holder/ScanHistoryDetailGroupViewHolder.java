package com.liandongfenqi.tongniu.tabfragmenteveryadd.ui.holder;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer.C;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.R;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter.BaseExpandableAdapter;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter.BasicExpandableGroupViewHolder2;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.ui.adapter.HistoryAdapter;

import java.lang.reflect.ParameterizedType;

import butterknife.BindView;
import butterknife.ButterKnife;
import gov.anzong.lunzi.util.AnzongLogUtils;

public class ScanHistoryDetailGroupViewHolder extends BasicExpandableGroupViewHolder2<String,String> {
    private HistoryAdapter mScanHistoryDetailViewAdapter;
    @BindView(R.id.barcode_format_name)
    TextView formatNameTv;
    @BindView(R.id.barcode_count)
    TextView barCodeCountTv;
    @BindView(R.id.expand_iv)
    ImageView expandIv;
    @BindView(R.id.divider)
    View divider;
    public ScanHistoryDetailGroupViewHolder(BaseExpandableAdapter<String, String> adapter, Context context) {
        super(adapter, context);
    }

    @Override
    public View getInflateView() {
        View rootView = View.inflate(context, R.layout.item_history_detail_group, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void bindGroupData(int groupPosition, boolean isExpanded) {
        if (getGroup(groupPosition) != null) {
            formatNameTv.setText(getGroup(groupPosition).toUpperCase());
            barCodeCountTv.setText(String.valueOf(adapter.getChildrenCount(groupPosition)));
            if (isExpanded) {
                expandIv.setImageResource(R.mipmap.list_zhankai_icon);
                formatNameTv.setTextColor(Color.BLUE);
                barCodeCountTv.setTextColor(Color.BLUE);
                divider.setVisibility(View.GONE);
            } else {
                expandIv.setImageResource(R.mipmap.list_shouqi_icon);
                formatNameTv.setTextColor(Color.BLACK);
                barCodeCountTv.setTextColor(Color.BLACK);
                divider.setVisibility(View.VISIBLE);
            }
        }

    }

}
