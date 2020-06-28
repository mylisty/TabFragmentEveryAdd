/*
 * {EasyGank}  Copyright (C) {2015}  {CaMnter}
 *
 * This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.
 * This is free software, and you are welcome to redistribute it
 * under certain conditions; type `show c' for details.
 *
 * The hypothetical commands `show w' and `show c' should show the appropriate
 * parts of the General Public License.  Of course, your program's commands
 * might be different; for a GUI interface, you would use an "about box".
 *
 * You should also get your employer (if you work as a programmer) or school,
 * if any, to sign a "copyright disclaimer" for the program, if necessary.
 * For more information on this, and how to apply and follow the GNU GPL, see
 * <http://www.gnu.org/licenses/>.
 *
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs.  If your program is a subroutine library, you
 * may consider it more useful to permit linking proprietary applications with
 * the library.  If this is what you want to do, use the GNU Lesser General
 * Public License instead of this License.  But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 */

package gov.anzong.lunzi.util;

import android.content.Context;
import android.widget.Toast;

/**
 * //   When I wrote this, only God and I understood what I was doing
 * //   Now, God only knows
 * //   作者： Anh Lai/竹井詩織里
 * //   邮箱：ymback@sayyoulove.me
 * //   创建时间：2016-11-26 13:09
 * //   这玩意的用处：TOAST通用函数
 */
public class AnzongToastUtils {

    public static Context mContext;
    public static boolean isShow = true;
    private static Toast mToast;

    public AnzongToastUtils(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        if (!isShow)
            return;
        if (message == null) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setText(message);
        }
        mToast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(int message) {
        if (!isShow)
            return;
        if (mToast == null) {
            mToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setText(message);
        }
        mToast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        if (!isShow) {
            return;
        }
        if (message == null) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
        } else {
            mToast.setDuration(Toast.LENGTH_LONG);
            mToast.setText(message);
        }
        mToast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(int message) {
        if (!isShow)
            return;
        if (mToast == null) {
            mToast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
        } else {
            mToast.setDuration(Toast.LENGTH_LONG);
            mToast.setText(message);
        }
        mToast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        if (!isShow)
            return;
        if (message == null) {
            return;
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, message, duration);
        } else {
            mToast.setDuration(duration);
            mToast.setText(message);
        }
        mToast.show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(int message, int duration) {
        if (!isShow)
            return;
        if (mToast == null) {
            mToast = Toast.makeText(mContext, message, duration);
        } else {
            mToast.setDuration(duration);
            mToast.setText(message);
        }
        mToast.show();
    }

}
