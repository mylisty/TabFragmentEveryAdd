package gov.anzong.lunzi.cache;

import android.graphics.Bitmap;

import gov.anzong.lunzi.util.AnzongImageFileCacheUtils;

/**
 * //   When I wrote this, only God and I understood what I was doing
 * //   Now, God only knows
 * //   作者： Anh Lai/竹井詩織里
 * //   邮箱：ymback@sayyoulove.me
 * //   创建时间：2016-11-26 17:37
 * //   这玩意的用处：
 */
public class AnzongImageFileCache {

    private String path;

    public AnzongImageFileCache(String path) {
        super();
        this.path = path;
    }

    public Bitmap getBitmap(String url) {
        return AnzongImageFileCacheUtils.getInstance(path).getImage(url);
    }

    public void putBitmap(String url, Bitmap bitmap) {
        AnzongImageFileCacheUtils.getInstance(path).saveBitmap(bitmap, url);
    }
}