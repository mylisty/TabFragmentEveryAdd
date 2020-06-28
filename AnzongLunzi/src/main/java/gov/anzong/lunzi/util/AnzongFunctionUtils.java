package gov.anzong.lunzi.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * //   When I wrote this, only God and I understood what I was doing
 * //   Now, God only knows
 * //   作者： Anh Lai/竹井詩織里
 * //   邮箱：ymback@sayyoulove.me
 * //   创建时间：2016-11-26 14:57
 * //   这玩意的用处：
 */
public class AnzongFunctionUtils {

    private AnzongFunctionUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 检查是否使用了WIFI
     * @param mContext 上下文
     * @return boolean 使用了WIFI为true
     */
    public static boolean isUsingWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }
}
