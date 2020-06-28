package gov.anzong.lunzi.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * //   When I wrote this, only God and I understood what I was doing
 * //   Now, God only knows
 * //   作者： Anh Lai/来伟鹏
 * //   邮箱：ymback@sayyoulove.me
 * //   创建时间：2016-11-01 09:30
 * //   这玩意的用处：
 */
public class AnzongMD5Utils {

    public static String getMD5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
