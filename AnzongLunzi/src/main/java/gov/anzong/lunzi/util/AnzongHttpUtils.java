package gov.anzong.lunzi.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import static android.content.ContentValues.TAG;

/**
 * //   When I wrote this, only God and I understood what I was doing
 * //   Now, God only knows
 * //   作者： Anh Lai/竹井詩織里
 * //   邮箱：ymback@sayyoulove.me
 * //   创建时间：2016-11-26 16:45
 * //   这玩意的用处：HTTP通用请求
 */
public class AnzongHttpUtils {

    private AnzongHttpUtils() {
        throw new AssertionError();
    }

    @SuppressWarnings("deprecation")
    public static String getHtml(String uri, String value) {
        InputStream is = null;
        final String USER_AGENT = "Mozilla/5.0 (Linux; U; Android 4.1) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", USER_AGENT);
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Connection", "close");
            conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
            conn.addRequestProperty("StorePrincipal", value);
            conn.addRequestProperty("Key", value);
            conn.addRequestProperty("Ticket", value);
            conn.setConnectTimeout(2500);
            conn.setReadTimeout(2500);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
            } else {
                is = conn.getErrorStream();
            }
            if ("gzip".equals(conn.getHeaderField("Content-Encoding"))) {
                is = new GZIPInputStream(is);
            }
            String encoding = getCharset(conn, "utf-8");
            return readFully(is, encoding);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String readFully(InputStream inputStream, String encoding)
            throws IOException {
        return new String(readFully(inputStream), encoding);
    }

    private static byte[] readFully(InputStream inputStream)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos.toByteArray();
    }

    public static String getCharset(HttpURLConnection conn, String defaultValue) {
        if (conn == null)
            return defaultValue;
        String contentType = conn.getHeaderField("Content-Type");
        if (AnzongStringUtils.isEmpty(contentType))
            return defaultValue;
        String startTag = "charset=";
        String endTag = " ";
        int start = contentType.indexOf(startTag);
        if (-1 == start)
            return defaultValue;
        start += startTag.length();
        int end = contentType.indexOf(endTag, start);
        if (-1 == end)
            end = contentType.length();
        if (contentType.substring(start, end).equals("no")) {
            return "utf-8";
        }
        return contentType.substring(start, end);
    }


    public static void downImage(String uri, String fileName) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            Bitmap bitmap = loadBitmap(uri);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (Exception e) {
            AnzongLogUtils.e(TAG, "failed to download img:" + uri + "," + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static Bitmap loadBitmap(String url) {
        Bitmap bm = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bm;
    }


    public static String SplitUrlWithparameters(String url, Map<String, String> params) {
        if (url.indexOf("?") <= 0) {
            url = url + "?anzong=now&";
        }
        if (params == null) {
            params = new HashMap<>();
        }
        for (Object key : params.keySet()) {
            Object val = params.get(key);
            url += key + "=" + val + "&";
        }
        return url;
    }
}
