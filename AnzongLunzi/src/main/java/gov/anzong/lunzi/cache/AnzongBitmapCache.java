package gov.anzong.lunzi.cache;

import android.graphics.Bitmap;

import androidx.collection.LruCache;

/**
 * //   When I wrote this, only God and I understood what I was doing
 * //   Now, God only knows
 * //   作者： Anh Lai/竹井詩織里
 * //   邮箱：ymback@sayyoulove.me
 * //   创建时间：2016-11-26 17:37
 * //   这玩意的用处：
 */
public class AnzongBitmapCache {
    private LruCache<String, Bitmap> mCache;

    public AnzongBitmapCache() {
        int maxSize = 8 * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}


