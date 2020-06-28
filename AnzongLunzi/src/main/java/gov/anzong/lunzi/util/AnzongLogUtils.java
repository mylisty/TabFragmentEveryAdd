package gov.anzong.lunzi.util;

import android.util.Log;

import gov.anzong.lunzi.BuildConfig;


/**
 * //   When I wrote this, only God and I understood what I was doing
 * //   Now, God only knows
 * //   作者： Anh Lai/竹井詩織里
 * //   邮箱：ymback@sayyoulove.me
 * //   创建时间：2016-11-26 14:20
 * //   这玩意的用处：Log打印工具
 */
public class AnzongLogUtils {
    private static final String TAG = AnzongLogUtils.class.getSimpleName();
    public static boolean isDebug = BuildConfig.DEBUG;// 是否需要打印bug，可以在application的onCreate函数里面初始化

    private AnzongLogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG,AnzongStringUtils.nullStrToEmpty(msg));
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG,AnzongStringUtils.nullStrToEmpty(msg));
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG,AnzongStringUtils.nullStrToEmpty(msg));
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG,AnzongStringUtils.nullStrToEmpty(msg));
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, AnzongStringUtils.nullStrToEmpty(msg));
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag,AnzongStringUtils.nullStrToEmpty(msg));
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag,AnzongStringUtils.nullStrToEmpty(msg));
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag,AnzongStringUtils.nullStrToEmpty(msg));
    }

}
