package com.liandongfenqi.tongniu.tabfragmenteveryadd.ui;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.R;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter.BaseExpandableDataBean;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.ui.adapter.HistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupListListActivity extends AppCompatActivity {
    @BindView(R.id.order_lv)
    ExpandableListView orderLv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_list);
        ButterKnife.bind(this);
        Bean data = new Gson().fromJson("{\n" +
                "        \"totalQuantity\": 1,\n" +
                "        \"items\": [\n" +
                "            {\n" +
                "                \"quantity\": 1,\n" +
                "                \"erpImportName\": \"Test\",\n" +
                "                \"barcodeItems\": [\n" +
                "                      \"B0W6F12792\",\n" +
                "                    \"B0W6F12792\",\n" +
                "                  \"B0W6F12792\",\n" +
                "                  \"B0W6F12792\"\n" +
                "                ]\n" +
                "            },\n" +
                "             {\n" +
                "                \"quantity\": 1,\n" +
                "                \"erpImportName\": \"Test2\",\n" +
                "                \"barcodeItems\": [\n" +
                "                      \"df\",\n" +
                "                    \"df\",\n" +
                "                  \"df\",\n" +
                "                  \"f\"\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    }", Bean.class);

//        List<BaseExpandableDataBean<String, String>> dataList = new ArrayList<>();
        List<BaseExpandableDataBean<String, String>> dataList = new ArrayList<>();
        for (int i = 0; i < data.getItems().size(); i++) {
            BaseExpandableDataBean<String, String> bean = new BaseExpandableDataBean<>();
            bean.groupObject = data.getItems().get(i).getErpImportName();
            bean.childList = data.getItems().get(i).getBarcodeItems();
            dataList.add(bean);
        }
        HistoryAdapter adapter = new HistoryAdapter(dataList,this);
        orderLv.setAdapter(adapter);
        orderLv.setGroupIndicator(null);
        if (adapter.getGroupCount() > 0) {
            orderLv.expandGroup(0);
        }
//        orderLv.setOnChildClickListener();
//        orderLv.setOnGroupClickListener((parent, v, groupPosition, id) -> {
//            if (orderLv.isGroupExpanded(groupPosition)) {
//                orderLv.collapseGroup(groupPosition);
//            } else {
//                for (int i = 0; i < adapter.getGroupCount(); i++) {
//                    orderLv.collapseGroup(i);
//                }
//                orderLv.expandGroup(groupPosition);
//            }
//            return true;
//        });
        orderLv.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> true);
    }
}
