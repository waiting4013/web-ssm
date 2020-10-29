package ssm.utils;

import org.apache.commons.lang3.StringUtils;
import test.DesEncrypt;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static final String US_ASCII = "US-ASCII";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String UTF_8 = "UTF-8";
    public static final String UTF_16BE = "UTF-16BE";
    public static final String UTF_16LE = "UTF-16LE";
    public static final String UTF_16 = "UTF-16";
    public static final String GBK = "GBK";
    public static final String GB2312 = "GB2312";

    public StringUtil() {
    }

    public static String toASCII(String str) throws UnsupportedEncodingException {
        return changeCharset(str, "US-ASCII");
    }

    public static String toISO_8859_1(String str) throws UnsupportedEncodingException {
        return changeCharset(str, "ISO-8859-1");
    }

    public static String toUTF_8(String str) throws UnsupportedEncodingException {
        return changeCharset(str, "UTF-8");
    }

    public static String toUTF_16BE(String str) throws UnsupportedEncodingException {
        return changeCharset(str, "UTF-16BE");
    }

    public static String toUTF_16LE(String str) throws UnsupportedEncodingException {
        return changeCharset(str, "UTF-16LE");
    }

    public static String toUTF_16(String str) throws UnsupportedEncodingException {
        return changeCharset(str, "UTF-16");
    }

    public static String toGBK(String str) throws UnsupportedEncodingException {
        return changeCharset(str, "GBK");
    }

    public static String toGB2312(String str) throws UnsupportedEncodingException {
        return changeCharset(str, "GB2312");
    }

    public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            byte[] bs = str.getBytes();
            return new String(bs, newCharset);
        } else {
            return null;
        }
    }

    public static String changeCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            byte[] bs = str.getBytes(oldCharset);
            return new String(bs, newCharset);
        } else {
            return null;
        }
    }

    public static boolean isEmpty(String value) {
        return value == null || "".equals(value.trim()) || "null".equalsIgnoreCase(value);
    }

    public static String[] split(String value, String split, boolean allowEmpty) {
        String[] result = split(value, split);
        String[] array = result;
        if (!allowEmpty && result != null && result.length > 0) {
            int length = 0;

            int i;
            for(i = 0; i < result.length; ++i) {
                if (result[i] != null && !result[i].equals("")) {
                    ++length;
                }
            }

            if (length > 0) {
                array = new String[length];
                i = 0;

                for(int var7 = 0; i < result.length; ++i) {
                    if (result[i] != null && !result[i].equals("")) {
                        array[var7++] = result[i];
                    }
                }
            }
        }

        return array;
    }

    public static String[] split(String value, String split) {
        if (value != null && split != null) {
            char[] temp_array = value.toCharArray();
            int strLength = value.length();
            int strInLength = split.length();
            int strInTimes = 0;
            int[] strIndex = new int[strLength];
            int i = 0;
            int ii = 0;

            int k;
            while(i <= strLength - strInLength) {
                String temp_str = "";

                for(k = i; k < i + strInLength; ++k) {
                    temp_str = temp_str + temp_array[k];
                }

                if (temp_str.equals(split)) {
                    ++strInTimes;
                    strIndex[ii] = i;
                    i += strInLength;
                    ++ii;
                } else {
                    ++i;
                }
            }

            String[] back_str;
            if (strInTimes < 1) {
                back_str = new String[]{value};
                return back_str;
            } else {
                back_str = new String[strInTimes + 1];
                back_str[0] = value.substring(0, strIndex[0]);

                for(k = 1; k < strInTimes; ++k) {
                    back_str[k] = value.substring(strIndex[k - 1] + strInLength, strIndex[k]);
                }

                back_str[strInTimes] = value.substring(strIndex[strInTimes - 1] + strInLength, value.length());
                return back_str;
            }
        } else {
            return null;
        }
    }

    public static String replace(String strSource, String strFrom, String strTo) {
        if (strFrom != null && !strFrom.equals("")) {
            String strDest = "";

            int intPos;
            for(int intFromLen = strFrom.length(); (intPos = strSource.indexOf(strFrom)) != -1; strSource = strSource.substring(intPos + intFromLen)) {
                strDest = strDest + strSource.substring(0, intPos);
                strDest = strDest + strTo;
            }

            strDest = strDest + strSource;
            return strDest;
        } else {
            return strSource;
        }
    }

    public static String replaceStar(String str) {
        StringBuffer temp = new StringBuffer();
        if (null != str && !"".equals(str.trim())) {
            if (str.length() >= 5) {
                String substring = str.substring(0, 2);
                String substring1 = str.substring(2, str.length() - 2);
                String substring2 = str.substring(str.length() - 2);
                temp.append(substring);

                for(int i = 0; i < substring1.length(); ++i) {
                    temp.append("*");
                }

                temp.append(substring2);
            } else {
                temp.append(str);
            }
        } else {
            temp.append("匿名用户");
        }

        return temp.toString();
    }

    public static String changeUnit(String val) {
        return changeUnit(val, "", 2);
    }

    public static String changeUnitByDefaultDivision(String val) {
        return changeUnit(val, "##", 2);
    }

    public static String changeUnitWithoutScale(String val, String division) {
        return changeUnit(val, division, 0);
    }

    public static String changeUnit(String val, String division, int scale) {
        int offset = val.indexOf(".");
        if (offset <= 0) {
            offset = val.length();
        }

        if (offset <= 4) {
            return removeScaleWhenScaleIsZero((new BigDecimal(val)).setScale(scale, 1).toString()) + division;
        } else if (offset >= 5 && offset <= 8) {
            return removeScaleWhenScaleIsZero((new BigDecimal(val)).divide(new BigDecimal(10000)).setScale(scale, 1).toString()) + division + "万";
        } else {
            return offset > 8 ? removeScaleWhenScaleIsZero((new BigDecimal(val)).divide(new BigDecimal(100000000)).setScale(scale, 1).toString()) + division + "千万" : val + division;
        }
    }

    public static String removeScaleWhenScaleIsZero(String val) {
        int offset = val.indexOf(".");
        if (offset == -1) {
            return val;
        } else {
            return isZeroValue(val.substring(offset + 1, val.length())) ? val.substring(0, offset) : val;
        }
    }

    public static boolean isZeroValue(String val) {
        try {
            return (new BigDecimal(val)).doubleValue() == BigDecimal.ZERO.doubleValue();
        } catch (Exception var2) {
            return false;
        }
    }

    public static String sha1(String str) {
        if (str != null && str.length() != 0) {
            char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

            try {
                MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
                mdTemp.update(str.getBytes());
                byte[] md = mdTemp.digest();
                int j = md.length;
                char[] buf = new char[j * 2];
                int k = 0;

                for(int i = 0; i < j; ++i) {
                    byte byte0 = md[i];
                    buf[k++] = hexDigits[byte0 >>> 4 & 15];
                    buf[k++] = hexDigits[byte0 & 15];
                }

                return new String(buf);
            } catch (Exception var9) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String hidePartPhoneNumber(String phone) {
        if (!isEmpty(phone)) {
            if (phone.length() > 16) {
                phone = DesEncrypt.desString(phone);
            }

            return phone.replaceAll("(?<=\\d{2})\\d(?=\\d{2})", "*");
        } else {
            return "";
        }
    }

    public static boolean isDecimal(String orginal) {
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);
    }

    private static boolean isMatch(String regex, String orginal) {
        if (orginal != null && !orginal.trim().equals("")) {
            Pattern pattern = Pattern.compile(regex);
            Matcher isNum = pattern.matcher(orginal);
            return isNum.matches();
        } else {
            return false;
        }
    }

    public static List<Integer> transformStationStr2List(String stationIds) {
        if (!StringUtils.isEmpty(stationIds) && !"0".equals(stationIds)) {
            List<Integer> list = new ArrayList();
            String[] var2 = stationIds.split(",");
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String str = var2[var4];
                if (StringUtils.isNumeric(str)) {
                    list.add(Integer.parseInt(str));
                }
            }

            return list;
        } else {
            return null;
        }
    }
}
