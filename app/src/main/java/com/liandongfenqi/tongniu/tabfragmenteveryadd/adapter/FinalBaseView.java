package com.liandongfenqi.tongniu.tabfragmenteveryadd.adapter;

import android.content.Context;
import android.content.Intent;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-08-08 11:29<br>
 * //   这玩意的用处：<br>
 */
public interface FinalBaseView<T> {
    void setPresenter(T presenter);

    Context getContext();

    T getPresenter();

    void startActivity(Intent mIntent);

    void startActivityForResult(Intent intent, int requestCode);

    void showSuccessToast(String successStr, boolean needFinish);

    void showErrorToast(String error);

    void showLoadingErrorToast();

    void showErrorToast(String error, int imageSource);

    void showLoadingToast();

    void showLoadingToast(String str, boolean isAutoHide);

    void showLoadingToast(String str);

    void dismissLoadingDialog(boolean needFinish);

    void dismissLoadingDialog();

    void showSimpleToast(CharSequence toastString);

    void showSimpleToast(int strResource);
}