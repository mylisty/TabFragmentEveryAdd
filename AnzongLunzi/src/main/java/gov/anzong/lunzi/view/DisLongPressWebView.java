package gov.anzong.lunzi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;

import java.lang.reflect.Field;

import gov.anzong.lunzi.BuildConfig;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2016-12-05 11:17<br>
 * //   这玩意的用处：禁用长按事件的webview<br>
 */
public class DisLongPressWebView extends WebView {
    long last_time = 0;

    public DisLongPressWebView(Context context) {
        super(context);
    }

    public DisLongPressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                long current_time = System.currentTimeMillis();
                long d_time = current_time - last_time;
                System.out.println(d_time);
                if (d_time < 300) {
                    last_time = current_time;
                    return true;
                } else {
                    last_time = current_time;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void destroy() {
        super.destroy();
        releaseAllWebViewCallback();
    }

    @SuppressLint("NewApi")
    public void releaseAllWebViewCallback() {
        if (android.os.Build.VERSION.SDK_INT < 16) {
            try {
                Field field = WebView.class.getDeclaredField("mWebViewCore");
                field = field.getType().getDeclaredField("mBrowserFrame");
                field = field.getType().getDeclaredField("sConfigCallback");
                field.setAccessible(true);
                field.set(null, null);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                try {
                    if (BuildConfig.DEBUG) {
                        e.printStackTrace();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        } else {
            try {
                Field sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (sConfigCallback != null) {
                    sConfigCallback.setAccessible(true);
                    sConfigCallback.set(null, null);
                }
            } catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e) {
                try {
                    if (BuildConfig.DEBUG) {
                        e.printStackTrace();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
