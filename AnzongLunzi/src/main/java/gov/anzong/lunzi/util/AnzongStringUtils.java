package gov.anzong.lunzi.util;

import android.annotation.SuppressLint;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gov.anzong.lunzi.bean.StringFindResult;

/**
 * Created by Anh Lai on 2015/11/5 0005.
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                            O\ = /O
 * //                        ____/`---'\____
 * //                      .   ' \\| |// `.
 * //                       / \\||| : |||// \
 * //                     / _||||| -:- |||||- \
 * //                       | | \\\ - /// | |
 * //                     | \_| ''\---/'' | |
 * //                      \ .-\__ `-` ___/-. /
 * //                   ___`. .' /--.--\ `. . __
 * //                ."" '< `.___\_<|>_/___.' >'"".
 * //               | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * //                 \ \ `-. \_ __\ /__ _/ .-` / /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //
 * //         .............................................
 * //                  佛祖保佑             永无BUG
 * //          佛曰:
 * //                  写字楼里写字间，写字间里程序员；
 * //                  程序人员写程序，又拿程序换酒钱。
 * //                  酒醒只在网上坐，酒醉还来网下眠；
 * //                  酒醉酒醒日复日，网上网下年复年。
 * //                  但愿老死电脑间，不愿鞠躬老板前；
 * //                  奔驰宝马贵者趣，公交自行程序员。
 * //                  别人笑我忒疯癫，我笑自己命太贱；
 * //                  不见满街漂亮妹，哪个归得程序员？
 */
public class AnzongStringUtils {

    private static final String ignoreCaseTag = "(?i)";

    private AnzongStringUtils() {
        throw new AssertionError();
    }

    /**
     * 是NULL或""或全由空格组成
     * <p>
     * isBlank(null) = true;
     * isBlank("") = true;
     * isBlank("  ") = true;
     * isBlank("a") = false;
     * isBlank("a ") = false;
     * isBlank(" a") = false;
     * isBlank("a b") = false;
     *
     * @param str
     * @return 是NULL或""或全由空格组成, return true, 否则 return false.
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * 是NULL或""或全由空格组成或者为0
     * <p>
     * isBlank(null) = true;
     * isBlank("") = true;
     * isBlank("  ") = true;
     * isBlank("a") = false;
     * isBlank("a ") = false;
     * isBlank(" a") = false;
     * isBlank("a b") = false;
     *
     * @param str
     * @return 是NULL或""或全由空格或者由一个0组成, return true, 否则 return false.
     */
    public static boolean isBlankOrZero(String str) {
        return (str == null || str.trim().length() == 0 || str.equals("0"));
    }


    public static boolean isMobilePhoneNumber(String str) {
        if (isBlank(str)) {
            return false;
        }
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * is null or its length is 0
     * <p>
     * isEmpty(null) = true;
     * isEmpty("") = true;
     * isEmpty("  ") = false;
     *
     * @param str
     * @return 空或者0返回真，否则返回假
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    /**
     * 比较2字符
     *
     * @param actual
     * @param expected
     * @return
     * @see AnzongObjectUtils#isEquals(Object, Object)
     */
    public static boolean isEquals(String actual, String expected) {
        return AnzongObjectUtils.isEquals(actual, expected);
    }

    /**
     * 获取字符串长度
     * <p>
     * length(null) = 0;
     * length(\"\") = 0;
     * length(\"abc\") = 3;
     *
     * @param str
     * @return 空或者0返回0，否则返回值 {@link CharSequence#length()}.
     */
    public static int length(CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * NULL字符串转空""
     * <p>
     * nullStrToEmpty(null) = "";
     * nullStrToEmpty("") = "";
     * nullStrToEmpty("aa") = "aa";
     *
     * @param str
     * @return
     */
    public static String nullStrToEmpty(Object str) {
        return (str == null ? "" : (str instanceof String ? (String) str : str.toString()));
    }

    /**
     * 首字符大写
     * <p>
     * capitalizeFirstLetter(null)     =   null;
     * capitalizeFirstLetter("")       =   "";
     * capitalizeFirstLetter("2ab")    =   "2ab"
     * capitalizeFirstLetter("a")      =   "A"
     * capitalizeFirstLetter("ab")     =   "Ab"
     * capitalizeFirstLetter("Abc")    =   "Abc"
     *
     * @param str
     * @return
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str : new StringBuilder(str.length())
                .append(Character.toUpperCase(c)).append(str.substring(1)).toString();
    }

    /**
     * encoded in utf-8
     * <p>
     * utf8Encode(null)        =   null
     * utf8Encode("")          =   "";
     * utf8Encode("aa")        =   "aa";
     * utf8Encode("��������")   = "%E5%95%8A%E5%95%8A%E5%95%8A%E5%95%8A";
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException 如果出现异常
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * UTF8ENCODE，失败返回默认返回值defultReturn
     *
     * @param str
     * @param defultReturn
     * @return
     */
    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    /**
     * 获取HTML标签中间的值
     * <p>
     * getHrefInnerHtml(null)                                  = ""
     * getHrefInnerHtml("")                                    = ""
     * getHrefInnerHtml("mp3")                                 = "mp3";
     * getHrefInnerHtml("<a innerHtml</a>")                    = "<a innerHtml</a>";
     * getHrefInnerHtml("<a>innerHtml</a>")                    = "innerHtml";
     * getHrefInnerHtml("<a<a>innerHtml</a>")                    = "innerHtml";
     * getHrefInnerHtml("<a href="baidu.com">innerHtml</a>")               = "innerHtml";
     * getHrefInnerHtml("<a href="baidu.com" title="baidu">innerHtml</a>") = "innerHtml";
     * getHrefInnerHtml("   <a>innerHtml</a>  ")                           = "innerHtml";
     * getHrefInnerHtml("<a>innerHtml</a></a>")                      = "innerHtml";
     * getHrefInnerHtml("jack<a>innerHtml</a></a>")                  = "innerHtml";
     * getHrefInnerHtml("<a>innerHtml1</a><a>innerHtml2</a>")        = "innerHtml2";
     *
     * @param href
     * @return null返回""
     * 没有匹配内容返回原串
     * 有匹配内容返回最后一个匹配值
     */
    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }

        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern.compile(hrefReg, Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }

    /**
     * 转换HTML转义字符至原始字符
     * <p>
     * htmlEscapeCharsToString(null) = null;
     * htmlEscapeCharsToString("") = "";
     * htmlEscapeCharsToString("mp3") = "mp3";
     * htmlEscapeCharsToString("mp3<") = "mp3<";
     * htmlEscapeCharsToString("mp3>") = "mp3\>";
     * htmlEscapeCharsToString("mp3&mp4") = "mp3&mp4";
     * htmlEscapeCharsToString("mp3"mp4") = "mp3\"mp4";
     * htmlEscapeCharsToString("mp3<>&"mp4") = "mp3\<\>&\"mp4";
     *
     * @param source
     * @return
     */
    public static String htmlEscapeCharsToString(String source) {
        return isEmpty(source) ? source : source.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    /**
     * 全角转半角
     * <p>
     * fullWidthToHalfWidth(null) = null;
     * fullWidthToHalfWidth("") = "";
     * fullWidthToHalfWidth(new String(new char[] {12288})) = " ";
     * fullWidthToHalfWidth("�������磥��) = "!\"#$%&";
     *
     * @param s
     * @return
     */
    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char) (source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * 半角转全角
     * <p>
     * halfWidthToFullWidth(null) = null;
     * halfWidthToFullWidth("") = "";
     * halfWidthToFullWidth(" ") = new String(new char[] {12288});
     * halfWidthToFullWidth("!\"#$%&) = "�������磥��";
     *
     * @param s
     * @return
     */
    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char) 12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char) (source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * 获取一个长字符串中两个短字符串中间的字符串
     *
     * @param data
     * @param begPosition
     * @param startStr
     * @param endStr
     * @return
     */
    public static StringFindResult getStringBetween(String data,
                                                    int begPosition, String startStr, String endStr) {
        StringFindResult ret = new StringFindResult();
        do {
            if (isEmpty(data) || begPosition < 0
                    || data.length() <= begPosition || isEmpty(startStr)
                    || isEmpty(startStr))
                break;

            int start = data.indexOf(startStr, begPosition);
            if (start == -1)
                break;

            start += startStr.length();
            int end = data.indexOf(endStr, start);
            if (end == -1)
                end = data.length();
            ret.result = data.substring(start, end);
            ret.position = end + endStr.length();

        } while (false);

        return ret;
    }

    /**
     * 格式化数字
     * <p>
     * 转换double类型到带指定位数小数，整数部分可设置逗号分位
     * 2121.1111,2,true
     * 返回2,121.11
     *
     * @param money
     * @param decimalDigits
     * @param needsGroup
     * @return
     */
    public static String getFormattedNumberValue(double money, int decimalDigits, boolean needsGroup) {
        double f1 = money;
        String returnStr = "";
        String returnStrXiaoshu = "";
        int integerNumber = (int) f1;
        double doubleNumber = Math.abs(f1 - (int) f1);
        if (decimalDigits > 0) {
            String format = "###.";
            for (int i = 0; i < decimalDigits; i++) {
                format += "0";
            }
            DecimalFormat df = new DecimalFormat(format);
            returnStrXiaoshu = df.format(doubleNumber);
            if (returnStrXiaoshu.startsWith("1.")) {//四舍五入进到了1
                returnStrXiaoshu = returnStrXiaoshu.substring(1);
                integerNumber++;
            }
        }
        if (money < 0 && integerNumber == 0) {
            returnStr = "-";
        }
        if (needsGroup) {
            DecimalFormat df1 = (DecimalFormat) DecimalFormat.getInstance();
            df1.setGroupingSize(3);
            returnStr += df1.format(integerNumber);
        } else {
            returnStr += String.valueOf(integerNumber);
        }
        returnStr += returnStrXiaoshu;
        return returnStr;
    }

    public static String getFormattedNumberValue(String money, int decimalDigits, boolean needsGroup) {
        double f1 = 0d;
        try {
            f1 = Double.parseDouble(money);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getFormattedNumberValue(f1, decimalDigits, needsGroup);
    }

    /**
     * 格式化数字
     * <p>
     * 转换double类型到带指定位数小数，整数部分可设置逗号分位
     * 2121,2,true
     * 返回2,121.00
     *
     * @param money
     * @param decimalDigits
     * @param needsGroup
     * @return
     */
    public static String getFormattedNumberValue(int money, int decimalDigits, boolean needsGroup) {
        double f1 = money;
        String returnStr;
        if (needsGroup) {
            DecimalFormat df1 = (DecimalFormat) DecimalFormat.getInstance();
            df1.setGroupingSize(3);
            returnStr = df1.format(f1);
        } else {
            returnStr = f1 + "";
        }
        if (decimalDigits > 0) {
            returnStr += ".";
        }
        for (int i = 0; i < decimalDigits; i++) {
            returnStr += "0";
        }
        return returnStr;
    }

    /**
     * 读取tel://后的号码
     *
     * @param url
     * @return
     */
    public static String getPhoneNumberFromUrl(String url) {
        url = url.replaceAll(ignoreCaseTag + "tel://(\\d+)/*", "$1");
        return url;
    }

    /**
     * 按照时间格式准换UNIX时间戳到时间字符串
     *
     * @param timestamp
     * @param formats
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String TimeStamp2Date(long timestamp, String formats) {
        return new SimpleDateFormat(formats).format(new Date(timestamp));
    }

    /**
     * 获取MD5值
     *
     * @param plainText
     * @return
     */
    public static String MD5(CharSequence plainText) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.toString().getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (byte aB : b) {
                i = aB;
                if (i < 0) i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }


    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    public static String convertStandardTimeToMonthDay(String standardTime) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(standardTime, pos);
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            String data = sdf1.format(strtodate);
            AnzongLogUtils.e("USE WAY 1");
            return data;
        } catch (Exception e) {
            AnzongLogUtils.e("USE WAY 2");
            String month = getStringBetween(standardTime, 0, "-", "-").result;
            String day = getStringBetween(standardTime, 5, "-", " ").result;
            return month + "/" + day;
        }
    }

    /**
     * 将字符串转移为ASCII码
     *
     * @param cnStr
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    /**
     * 检测邮箱地址是否合法
     *
     * @param email
     * @return true合法 false不合法
     */
    public static boolean isEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 拼接字符串
     *
     * @param source
     * @param str
     * @return 拼接好的字符串
     */
    public static String splitString(String[] source, String str) {
        if (source == null || source.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int offset = source.length - 1;
        for (int i = 0; i < offset; i++) {
            sb.append(source[i]).append(str);
        }
        sb.append(source[offset]);
        return sb.toString();
    }

    /**
     * 拼接字符串
     *
     * @param source
     * @param str
     * @return 拼接好的字符串
     */
    public static String splitString(List<String> source, String str) {
        if (source == null || source.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int offset = source.size() - 1;
        for (int i = 0; i < offset; i++) {
            sb.append(source.get(i)).append(str);
        }
        sb.append(source.get(offset));
        return sb.toString();
    }

    /**
     * 获取首字母ascii
     *
     * @param value
     * @return
     */
    public static int getFirstLetterAscii(String value) {
        if (isBlank(value)) {
            return 0;
        }
        char[] chars = value.toCharArray();
        return chars[0];
    }

    public static boolean isSixLetterCarNumber(String carNumber) {
        return carNumber.matches("[A-z][A-Za-z0-9]{5}");
    }


    public static String nullStrToLine(String str) {
        return AnzongStringUtils.isBlank(str) ? "-" : str;
    }
}
