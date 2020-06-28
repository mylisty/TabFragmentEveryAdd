package com.liandongfenqi.tongniu.tabfragmenteveryadd.ui.classes;


import com.trello.rxlifecycle2.android.ActivityEvent;

import gov.anzong.lunzi.construct.FinalBasePresenter;
import gov.anzong.lunzi.util.AnzongLogUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2016-11-30 10:21<br>
 * //   这玩意的用处：<br>
 */
public abstract class BaseModel<T> {
    private static final String TAG = BaseModel.class.getSimpleName();
    protected FinalBasePresenter mFinalBasePresenter;
    protected Observer<T> mObserver;

    public BaseModel(FinalBasePresenter mIBasePresenter, Observable<T> mObservable, Observer<T> mObserver) {
        this(mIBasePresenter, mObservable, mObserver, false);
    }

    public BaseModel(FinalBasePresenter mIBasePresenter, Observable<T> mObservable, Observer<T> mObserver, boolean onPause) {
        this.mFinalBasePresenter = mIBasePresenter;
        this.mObserver = mObserver;
        try {
            initRxJavaObserver(mObservable, mObserver, onPause);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    private <T> void initRxJavaObserver(Observable<T> mObservable, Observer<T> mObserver, boolean onPause) throws Throwable {
        AnzongLogUtils.i(TAG, "InitRxJavaObserver");
        if (null == mObservable) {
            AnzongLogUtils.e(TAG, "InitRxJavaObserver.Faild.Null.Observable");
            throw new Throwable("InitRxJavaObserver.Faild.Null.Observable");
        }
        if (null == mObserver) {
            AnzongLogUtils.e(TAG, "InitRxJavaObserver.Faild.Null.Observer");
            throw new Throwable("InitRxJavaObserver.Faild.Null.Observer");
        }
        if (null == mFinalBasePresenter || null == mFinalBasePresenter.getRxAppCompatActivity()) {
            AnzongLogUtils.e(TAG, "InitRxJavaObserver.Faild.Null.Presenter");
            throw new Throwable("InitRxJavaObserver.Faild.Null.Presenter");
        }
        mObservable.compose(
                onPause ? mFinalBasePresenter.getRxAppCompatActivity().bindUntilEvent(ActivityEvent.PAUSE) :
                        mFinalBasePresenter.getRxAppCompatActivity().bindToLifecycle()
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
    }
}
