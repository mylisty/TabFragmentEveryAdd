package com.liandongfenqi.tongniu.tabfragmenteveryadd.ui;


import com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter.BaseExpandableDataBean;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter.FinalBaseView;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.ui.classes.IBasePresenter;
import com.liandongfenqi.tongniu.tabfragmenteveryadd.ui.classes.IBaseView;

import java.util.List;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2016-12-06 13:32<br>
 * //   这玩意的用处：<br>
 */
public class ScanHistoryDetailConstruct {
    public interface IScanHistoryDetailPresenter extends IBasePresenter {


    }
    public interface IScanHistoryDetailView extends IBaseView<IScanHistoryDetailPresenter> {
        void writeData(List<BaseExpandableDataBean<String, String>> data, int count);
    }
}
