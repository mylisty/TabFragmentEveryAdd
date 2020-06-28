package gov.anzong.lunzi.construct;

import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-08-08 11:27<br>
 * //   这玩意的用处：<br>
 */
public interface FinalBasePresenter {
    void start();

    void recycle();

    void onResume();

    void onPause();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    RxAppCompatActivity getRxAppCompatActivity();
}
