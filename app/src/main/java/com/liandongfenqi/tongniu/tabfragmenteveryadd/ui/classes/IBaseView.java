package com.liandongfenqi.tongniu.tabfragmenteveryadd.ui.classes;

import android.content.Intent;

import androidx.appcompat.widget.Toolbar;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import gov.anzong.lunzi.construct.FinalBaseView;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2016-11-28 09:40<br>
 * //   这玩意的用处：基础类的V层调用<br>
 */
public interface IBaseView<T> extends FinalBaseView<T> {
    void finish();

    void setResult(int resultCode, Intent mIntent);

    void closeInput();

    RxAppCompatActivity getRxAppCompatActivity();


    Toolbar getActionBarToolbar();

    void setToolbarTitle(String str);

    void setBackgroundAlpha(float bgAlpha);

//    void showHasNewVersionDialog(IBaseView iBaseView, VersionUpdateBean versionUpdateBean);
//
//    void showDownloadingDialog(IBaseView mIBaseView, VersionUpdateBean bean);
}
