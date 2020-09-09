package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.*;
import java.sql.Date;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Tools {
    private static String errMsg = "";
    private static final int MAXROWS = 5000;
    private static final String DASH = "[-_]";
    private static Hashtable<String, TreeMap<String, Object[]>> cacheClassFields = new Hashtable();
    private static final Logger LOGGER = LoggerFactory.getLogger(Tools.class);

    public Tools() {
    }

    public static boolean nullToBoolean(Object value) {
        return nullToBoolean(value, false);
    }

    public static boolean nullToBoolean(Object value, boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        } else {
            try {
                String strValue = value.toString();
                if (!strValue.trim().equals("on") && !strValue.trim().equals("yes") && !strValue.trim().equals("true") && !strValue.trim().equals("1")) {
                    return !strValue.trim().equals("off") && !strValue.trim().equals("no") && !strValue.trim().equals("false") && !strValue.trim().equals("0") ? Boolean.parseBoolean(value.toString()) : false;
                } else {
                    return true;
                }
            } catch (Exception var3) {
                LOGGER.error("Tools.nullToBoolean异常", var3);
                return defaultValue;
            }
        }
    }

    public static BigDecimal stringToBigDecimal(String value) {
        BigDecimal retValue = new BigDecimal("0.00");
        if (value == null) {
            return retValue;
        } else {
            try {
                retValue = new BigDecimal(value);
            } catch (Exception var3) {
                retValue = new BigDecimal("0.00");
            }

            return retValue;
        }
    }

    public static String nullToString(String value) {
        return value == null ? "" : value;
    }

    public static String nullToString(Object value) {
        return value == null ? "" : value.toString();
    }

    public static int nullToInteger(String value) {
        try {
            return null != value && !"".equals(value) ? Integer.valueOf(value) : 0;
        } catch (Exception var2) {
            LOGGER.error("Tools.nullToInteger异常", var2);
            return 0;
        }
    }

    public static String nullToString(String value, String defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static String nullToString(String value, String defaultValue, boolean useGBK) {
        String strRet = nullToString(value, defaultValue);
        return nullToString(strRet, useGBK);
    }

    public static String nullToString(String value, boolean useGBK) {
        if (value == null) {
            return "";
        } else if (!useGBK) {
            return value;
        } else {
            try {
                return new String(value.getBytes("ISO-8859-1"), "UTF-8");
            } catch (Exception var3) {
                LOGGER.error("Tools.nullToString异常", var3);
                return value;
            }
        }
    }

    public static String formatCurrency(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }

    public static boolean isExistsUrl(String url) {
        try {
            URL objUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)objUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            if (connection.getResponseCode() != 200) {
                return true;
            }
        } catch (Exception var3) {
            LOGGER.error("Tools.isExistsUrl异常", var3);
        }

        return false;
    }

    public static String paddingStringLeft(String s, int width, char ch) {
        if (s == null) {
            s = "";
        }

        for(int i = s.length(); i < width; ++i) {
            s = ch + s;
        }

        return s;
    }

    public static String paddingStringRight(String s, int width, char ch) {
        if (s == null) {
            s = "";
        }

        for(int i = s.length(); i < width; ++i) {
            s = s + ch;
        }

        return s;
    }

    public static String getErrMsg() {
        return errMsg == null ? "" : errMsg.replace('\'', ' ').replace('"', ' ');
    }

    public static void setErrMsg(String string) {
        errMsg = string;
    }

    public static boolean isExistsField(Class clz, String fieldName) {
        boolean bRet = false;
        if (clz != null && fieldName != null && !"".equals(fieldName)) {
            fieldName = fieldName.toUpperCase();
            TreeMap<String, Object[]> fields = getFieldMethodMap(clz);
            if (fields.get(fieldName) != null) {
                bRet = true;
            }

            return bRet;
        } else {
            return bRet;
        }
    }

    public static TreeMap<String, Object[]> getFieldMethodMap(Class clz) {
        TreeMap<String, Object[]> map = new TreeMap();
        if (cacheClassFields.containsKey(clz.getName())) {
            TreeMap<String, Object[]> treeMap = (TreeMap)cacheClassFields.get(clz.getName());
            return (TreeMap)treeMap.clone();
        } else {
            while(clz != Object.class) {
                Field[] fields = clz.getDeclaredFields();

                for(int i = 0; i < fields.length; ++i) {
                    int mod = fields[i].getModifiers();
                    if (!Modifier.isFinal(mod) && !Modifier.isStatic(mod)) {
                        String fieldName = fields[i].getName();
                        Method setMethod = null;
                        Method getMethod = null;

                        try {
                            String tmp = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                            setMethod = clz.getMethod("set" + tmp, fields[i].getType());
                            getMethod = clz.getMethod("get" + tmp, (Class[])null);
                        } catch (Exception var9) {
                            LOGGER.error("Tools.getFieldMethodMap异常", var9);
                        }

                        if (getMethod != null && setMethod != null) {
                            Object[] objs = new Object[]{setMethod, getMethod, fields[i]};
                            map.put(fieldName.toUpperCase(), objs);
                        }
                    }
                }

                clz = clz.getSuperclass();
            }

            if (map.size() > 0) {
                cacheClassFields.put(clz.getName(), map);
            }

            return map;
        }
    }

    private static Method[] getMethods(Class clz, ResultSet rs, int pos) throws SQLException {
        TreeMap<String, Object[]> map = getFieldMethodMap(clz);
        ResultSetMetaData meta = rs.getMetaData();
        Method[] methods = new Method[meta.getColumnCount()];

        for(int i = 0; i < methods.length; ++i) {
            String colName = meta.getColumnLabel(i + 1).toUpperCase();
            colName = colName.substring(colName.indexOf(46) + 1);
            Object[] objs = (Object[])((Object[])map.get(colName));
            if (objs == null) {
                colName = colName.replaceAll("[-_]", "");
                objs = (Object[])((Object[])map.get(colName));
            }

            if (objs != null) {
                methods[i] = (Method)objs[pos];
            }
        }

        return methods;
    }

    private static Method[] getMethods(Class clz, int pos) {
        TreeMap<String, Object[]> map = getFieldMethodMap(clz);
        Object[][] fields = new Object[map.size()][3];
        map.values().toArray(fields);
        Method[] methods = new Method[fields.length];

        for(int i = 0; i < fields.length; ++i) {
            methods[i] = (Method)fields[i][pos];
        }

        return methods;
    }

    public static String getDeleteSQL(Class clz, String tableName, String[] keys) {
        if (keys != null && keys.length != 0) {
            TreeMap<String, Object[]> map = getFieldMethodMap(clz);
            StringBuffer sql = new StringBuffer("delete from " + tableName + " where ");

            for(int i = 0; i < keys.length; ++i) {
                Object[] field = (Object[])((Object[])map.get(keys[i].toUpperCase()));
                if (field == null) {
                    field = (Object[])((Object[])map.get(keys[i].replaceAll("[-_]", "").toUpperCase()));
                }

                if (field == null) {
                    return "";
                }

                sql.append(keys[i] + "=? and ");
            }

            sql.delete(sql.length() - 5, sql.length());
            return sql.toString();
        } else {
            return "";
        }
    }

    public static ArrayList getResultSet(Class clz, ResultSet rs, int count) throws Exception {
        if (count <= 0) {
            count = 5000;
        }

        ArrayList list = new ArrayList();
        Method[] methods = getMethods(clz, rs, 0);
        int var5 = 0;

        while(rs.next() && var5++ < count) {
            Object obj = clz.newInstance();
            setFieldValue(methods, obj, rs);
            list.add(obj);
        }

        return list;
    }

    public static void setFieldValue(Method[] methods, Object obj, ResultSet rs) throws SQLException {
        for(int j = 0; j < methods.length; ++j) {
            if (methods[j] != null) {
                Object value = null;
                Class fieldClass = methods[j].getParameterTypes()[0];
                if (fieldClass != Byte.TYPE && fieldClass != Byte.class) {
                    if (fieldClass != Short.TYPE && fieldClass != Short.class) {
                        if (fieldClass != Integer.TYPE && fieldClass != Integer.class) {
                            if (fieldClass != Long.TYPE && fieldClass != Long.class) {
                                if (fieldClass != Float.TYPE && fieldClass != Float.class) {
                                    if (fieldClass != Double.TYPE && fieldClass != Double.class) {
                                        if (fieldClass != Boolean.TYPE && fieldClass != Boolean.class) {
                                            if (fieldClass == String.class) {
                                                value = rs.getString(j + 1);
                                            } else if (fieldClass == Date.class) {
                                                value = rs.getDate(j + 1);
                                            } else if (fieldClass == Time.class) {
                                                value = rs.getTime(j + 1);
                                            } else if (fieldClass == Timestamp.class) {
                                                value = rs.getTimestamp(j + 1);
                                            } else {
                                                value = rs.getObject(j + 1);
                                            }
                                        } else {
                                            value = rs.getBoolean(j + 1);
                                        }
                                    } else {
                                        value = new Double(rs.getDouble(j + 1));
                                    }
                                } else {
                                    value = new Float(rs.getFloat(j + 1));
                                }
                            } else {
                                value = new Long(rs.getLong(j + 1));
                            }
                        } else {
                            value = new Integer(rs.getInt(j + 1));
                        }
                    } else {
                        value = new Short(rs.getShort(j + 1));
                    }
                } else {
                    value = new Byte(rs.getByte(j + 1));
                }

                try {
                    methods[j].invoke(obj, value);
                } catch (Exception var7) {
                    LOGGER.error("Tools.setFieldValue异常", var7);
                }
            }
        }

    }

    private static void setSQLNull(PreparedStatement pstmt, Class clazz, int column) throws Exception {
        if (clazz == String.class) {
            pstmt.setString(column, (String)null);
        } else if (clazz == Timestamp.class) {
            pstmt.setTimestamp(column, (Timestamp)null);
        } else if (clazz != java.util.Date.class && clazz != java.util.Date.class) {
            if (clazz == Time.class) {
                pstmt.setTime(column, (Time)null);
            } else {
                pstmt.setObject(column, (Object)null);
            }
        } else {
            pstmt.setDate(column, (Date)null);
        }

    }

    public static Map getProperties(Object obj) {
        TreeMap<String, Object[]> treeMap = getFieldMethodMap(obj.getClass());
        Object[][] fields = new Object[treeMap.size()][3];
        treeMap.values().toArray(fields);
        HashMap<String, Object> map = new HashMap();

        for(int i = 0; i < fields.length; ++i) {
            Method method = (Method)fields[i][1];
            Field field = (Field)fields[i][2];

            try {
                Object value = method.invoke(obj, (Object[])null);
                map.put(field.getName(), value);
            } catch (Exception var8) {
                LOGGER.error("Tools.getProperties异常", var8);
            }
        }

        return map;
    }

    public static void outputMap(Map map, boolean useGBK) throws Exception {
        Iterator iter = map.entrySet().iterator();

        while(iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            Object key = entry.getKey();
            Object obj = entry.getValue();
            Object[] objs;
            if (obj != null && obj.getClass().getComponentType() != null) {
                objs = (Object[])((Object[])obj);
            } else {
                objs = new Object[]{obj};
            }

            for(int i = 0; i < objs.length; ++i) {
                String sKey = key.toString();
                String sValue = objs[i] == null ? null : objs[i].toString();
                if (useGBK) {
                    sKey = new String(sKey.getBytes("ISO-8859-1"), "GBK");
                    if (sValue != null) {
                        sValue = new String(sValue.getBytes("ISO-8859-1"), "GBK");
                    }
                }

                LOGGER.info("key={}, value={}", sKey, sValue);
            }
        }

    }

    public static String convertHTMLTag(String s) {
        return s == null ? s : s.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    public static int doubleToInteger(double objNumber) {
        return objNumber == 0.0D ? 0 : (int)objNumber;
    }

    public static long bigDecimalToLong(BigDecimal value) {
        if (value == null) {
            return 0L;
        } else {
            long result = 0L;

            try {
                result = value.longValue();
            } catch (Exception var4) {
                LOGGER.error("Tools.bigDecimalToLong异常", var4);
            }

            return result;
        }
    }

    public static long objectToLong(Object objNumber) {
        return objectToLong(objNumber, 0L);
    }

    public static long objectToLong(Object objNumber, long defaultValue) {
        return objNumber == null ? defaultValue : stringToLong(objNumber.toString(), defaultValue);
    }

    public static long longToLong(Long value) {
        return longToLong(value, 0L);
    }

    public static long longToLong(Long value, long defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static long stringToLong(String strNumber) {
        return stringToLong(strNumber, 0L);
    }

    public static long stringToLong(String strNumber, long defaultValue) {
        long result = defaultValue;

        try {
            result = Long.parseLong(strNumber);
        } catch (Exception var6) {
            LOGGER.error("Tools.stringToLong异常", var6);
        }

        return result;
    }

    public static byte stringToByte(String strNumber) {
        return stringToByte(strNumber, (byte)0);
    }

    public static Byte stringToByte(String strNumber, Byte defaultValue) {
        Byte result = defaultValue;

        try {
            result = Byte.parseByte(strNumber);
        } catch (Exception var4) {
        }

        return result;
    }

    public static int stringToInt(String strNumber) {
        return stringToInt(strNumber, 0);
    }

    public static boolean stringToBool(String value) {
        return stringToBool(value, false);
    }

    public static boolean stringToBool(String value, boolean defaultValue) {
        if (value != null && !"".equals(value)) {
            value = value.trim();
            if (value.equalsIgnoreCase("on") || value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("1")) {
                return true;
            }

            if (value.equalsIgnoreCase("off") || value.equalsIgnoreCase("false") || value.equalsIgnoreCase("no") || value.equalsIgnoreCase("0")) {
                return false;
            }
        }

        return defaultValue;
    }

    public static boolean booleanToBool(Boolean value, boolean defaultValue) {
        if (value == null) {
            return defaultValue;
        } else {
            boolean result = value;
            return result;
        }
    }

    public static boolean booleanToBool(Boolean value) {
        return booleanToBool(value, false);
    }

    public static int longToInt(long longValue) {
        return longToInt(longValue, 0);
    }

    public static int longToInt(long longValue, int defaultValue) {
        try {
            return (int)longValue;
        } catch (Exception var4) {
            return defaultValue;
        }
    }

    public static short integerToShort(Integer intValue) {
        return integerToShort(intValue, (short)0);
    }

    public static short integerToShort(Integer intValue, short defaultValue) {
        if (intValue == null) {
            return defaultValue;
        } else {
            try {
                return intValue.shortValue();
            } catch (Exception var3) {
                LOGGER.error("Tools.integerToShort异常", var3);
                return defaultValue;
            }
        }
    }

    public static boolean integerToBoolean(Integer intValue) {
        return integerToBoolean(intValue, false);
    }

    public static boolean integerToBoolean(Integer intValue, boolean defaultValue) {
        if (intValue == null) {
            return defaultValue;
        } else {
            return intValue > 0;
        }
    }

    public static byte integerToByte(Integer intValue) {
        return intValue == null ? 0 : intValue.byteValue();
    }

    public static int integerToInt(Integer intValue) {
        return integerToInt(intValue, 0);
    }

    public static int shortToInt(Short shortValue) {
        return shortToInt(shortValue, 0);
    }

    public static short shortToShort(Short shortValue) {
        return shortToShort(shortValue, (short)0);
    }

    public static short shortToShort(Short shortValue, short defaultValue) {
        return shortValue == null ? defaultValue : shortValue;
    }

    public static int byteToInt(Byte byteValue) {
        return byteToInt(byteValue, 0);
    }

    public static byte byteToByte(Byte byteValue) {
        return byteToByte(byteValue, (byte)0);
    }

    public static byte byteToByte(Byte byteValue, byte defaultValue) {
        byte result = defaultValue;
        if (byteValue != null) {
            result = byteValue;
        }

        return result;
    }

    public static int integerToInt(Integer intValue, int defaultValue) {
        int result = defaultValue;
        if (intValue != null) {
            result = intValue;
        }

        return result;
    }

    public static int shortToInt(Short shortValue, int defaultValue) {
        int result = defaultValue;
        if (shortValue != null) {
            result = shortValue.intValue();
        }

        return result;
    }

    public static int byteToInt(Byte byteValue, int defaultValue) {
        int result = defaultValue;
        if (byteValue != null) {
            result = byteValue.intValue();
        }

        return result;
    }

    public static boolean objectToBoolean(Object value) {
        return objectToBoolean(value, false);
    }

    public static boolean objectToBoolean(Object value, boolean defaultValue) {
        return value == null ? defaultValue : stringToBool(value.toString(), defaultValue);
    }

    public static int objectToInt(Object value) {
        return objectToInt(value, 0);
    }

    public static int objectToInt(Object value, int defaultValue) {
        return value == null ? defaultValue : stringToInt(value.toString());
    }

    public static Integer stringToInt(String strNumber, Integer defaultValue) {
        Integer result = defaultValue;
        if (StringUtil.isEmpty(strNumber)) {
            return defaultValue;
        } else {
            try {
                result = Integer.parseInt(strNumber);
            } catch (Exception var4) {
                LOGGER.error("Tools.stringToInt异常", var4);
            }

            return result;
        }
    }

    public static float stringTofloat(String strNumber, float defaultValue) {
        float result = defaultValue;

        try {
            result = Float.parseFloat(strNumber);
        } catch (Exception var4) {
            LOGGER.error("Tools.stringTofloat异常", var4);
        }

        return result;
    }

    public static float stringTofloat(String strNumber) {
        return stringTofloat(strNumber, 0.0F);
    }

    public static short stringToShort(String strNumber) {
        return stringToShort(strNumber, (short)0);
    }

    public static short stringToShort(String strNumber, short defaultValue) {
        short result = defaultValue;

        try {
            result = Short.parseShort(strNumber);
        } catch (Exception var4) {
            LOGGER.error("Tools.stringToShort异常", var4);
        }

        return result;
    }

    public static double objectToDouble(Object objNumber) {
        return objectToDouble(objNumber, 0.0D);
    }

    public static double objectToDouble(Object objNumber, double defaultValue) {
        double result = defaultValue;
        if (objNumber == null) {
            return defaultValue;
        } else {
            try {
                result = Double.parseDouble(objNumber.toString());
            } catch (Exception var6) {
            }

            return result;
        }
    }

    public static double stringToDouble(String strNumber) {
        return stringToDouble(strNumber, 0.0D);
    }

    public static double stringToDouble(String strNumber, double defaultValue) {
        double result = defaultValue;

        try {
            result = Double.parseDouble(strNumber);
        } catch (Exception var6) {
        }

        return result;
    }

    public static java.util.Date stringToDate(String strDate) {
        return stringToDate(strDate, new java.util.Date(0L));
    }

    public static java.util.Date stringToDate(String strDate, java.util.Date defaultValue) {
        Object date = defaultValue;

        try {
            date = Date.valueOf(strDate);
        } catch (Exception var4) {
        }

        return (java.util.Date)date;
    }

    public static java.util.Date stringToDate(String strDate, String strFormat) {
        SimpleDateFormat fromat = new SimpleDateFormat(strFormat);

        try {
            return fromat.parse(strDate);
        } catch (ParseException var4) {
            return null;
        }
    }

    public static Date stringToSqlDate(String strDate) {
        return stringToSqlDate(strDate, new Date(0L));
    }

    public static Date stringToSqlDate(String strDate, Date defaultValue) {
        Date date = defaultValue;

        try {
            date = Date.valueOf(strDate);
        } catch (Exception var4) {
        }

        return date;
    }

    public static Date stringToSqlDate(String strDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        java.util.Date date = null;

        try {
            date = sdf.parse(strDate);
        } catch (ParseException var5) {
            return null;
        }

        return new Date(date.getTime());
    }

    public static Time stringToSqlTime(String strTime) {
        return stringToSqlTime(strTime, new Time(0L));
    }

    public static Time stringToSqlTime(String strTime, Time defaultValue) {
        Time time = defaultValue;

        try {
            time = Time.valueOf(strTime);
        } catch (Exception var4) {
        }

        return time;
    }

    public static Timestamp stringToTimestamp(String strTime) {
        return stringToTimestamp(strTime, new Timestamp(0L));
    }

    public static Timestamp stringToTimestamp(String strTime, Timestamp defaultValue) {
        Timestamp time = defaultValue;

        try {
            time = Timestamp.valueOf(strTime);
        } catch (Exception var4) {
            LOGGER.error("Tools.stringToTimestamp异常", var4);
        }

        return time;
    }

    public static void createFile(String info, String logfile) {
        try {
            FileWriter pw = new FileWriter(logfile);
            Throwable var3 = null;

            try {
                pw.write(info);
                pw.close();
            } catch (Throwable var13) {
                var3 = var13;
                throw var13;
            } finally {
                if (pw != null) {
                    if (var3 != null) {
                        try {
                            pw.close();
                        } catch (Throwable var12) {
                            var3.addSuppressed(var12);
                        }
                    } else {
                        pw.close();
                    }
                }

            }
        } catch (Exception var15) {
            LOGGER.error("Tools.createFile异常", var15);
        }

    }

    public static void writeTopList(String strCondition, OutputStream out) {
        try {
            out.write(strCondition.getBytes());
        } catch (Exception var3) {
            LOGGER.error("Tools.writeTopList异常", var3);
        }

    }

    public static int GenerateRandom(int min, int max) {
        if (min == max) {
            return max;
        } else {
            int number = 0;

            try {
                do {
                    number = (int)Math.round(Math.random() * (double)max);
                } while(number < min);
            } catch (Exception var4) {
                LOGGER.error("Tools.GenerateRandom异常", var4);
            }

            return number;
        }
    }

    public static String getSimpleStr(String str) {
        return str != null && !str.trim().equalsIgnoreCase("null") ? str.trim() : "";
    }

    public static String trimBeginEndChar(String str, String charStr) {
        if (str != null && !str.trim().equalsIgnoreCase("null")) {
            str = str.trim();
            charStr = charStr.trim();
            if (charStr != null && !charStr.equals("")) {
                int charStrLength;
                for(charStrLength = charStr.length(); str.startsWith(charStr); str = str.substring(charStrLength)) {
                }

                while(str.endsWith(charStr)) {
                    str = str.substring(0, str.length() - charStrLength);
                }

                return str;
            } else {
                return str;
            }
        } else {
            return "";
        }
    }

    public static String replaceSpecialToCnChar(String str) {
        if (str != null && !"".equals(str)) {
            str = str.replaceAll("'", "��");
            str = str.replaceAll("\"", "��");
            return str;
        } else {
            return str;
        }
    }

    public static String strToBigInt(String str) {
        return str != null && !"".equals(str) ? String.valueOf(new BigInteger(str.getBytes())) : "";
    }

    public static String bigIntToStr(String strBigInteger) {
        return strBigInteger != null && !"".equals(strBigInteger) ? new String((new BigInteger(strBigInteger)).toByteArray()) : "";
    }

    public static String ContentFilter(List<String> keyWordList, String content, String replaceStr) {
        content = nullToString(content);
        if ("".equals(content)) {
            return content;
        } else if (keyWordList != null && keyWordList.size() != 0) {
            for(int i = 0; i < keyWordList.size(); ++i) {
                String keyWord = (String)keyWordList.get(i);
                content = content.replaceAll(keyWord, replaceStr);
            }

            return content;
        } else {
            return content;
        }
    }

    public static boolean hostAlive(String host) {
        if (host != null && host.length() != 0) {
            int pos = host.indexOf(58);
            return pos > 0 ? hostAlive(host.substring(0, pos), stringToInt(host.substring(pos + 1))) : hostAlive(host, 80);
        } else {
            return false;
        }
    }

    public static boolean hostAlive(String host, int port) {
        try {
            InetSocketAddress sockAddr = new InetSocketAddress(host, port);
            Socket socket = new Socket();
            socket.connect(sockAddr, 500);
            socket.close();
            return true;
        } catch (Exception var4) {
            LOGGER.error("Tools.hostAlive异常", var4);
            return false;
        }
    }

    public static String readTextFile(String fileName) {
        if (fileName != null && fileName.length() != 0) {
            File file = new File(fileName);
            if (file.isFile() && file.canRead()) {
                if (file.length() > 1048576L) {
                    setErrMsg("�ļ����ݳ��ȳ���1M!");
                    return "";
                } else {
                    FileReader fr = null;
                    String rc = "";

                    try {
                        fr = new FileReader(fileName);
                        char[] buff = new char[(int)file.length()];
                        fr.read(buff);
                        rc = new String(buff);
                    } catch (IOException var13) {
                        LOGGER.error("Tools.readTextFile异常", var13);
                    } finally {
                        if (fr != null) {
                            try {
                                fr.close();
                            } catch (Exception var12) {
                            }
                        }

                    }

                    return rc;
                }
            } else {
                setErrMsg("�ļ�������!");
                return "";
            }
        } else {
            setErrMsg("�ļ�����Ч!");
            return "";
        }
    }

    public static boolean writeTextFile(String fileName, String content) {
        if (fileName != null && fileName.length() != 0) {
            FileOutputStream fos = null;
            boolean rc = false;

            try {
                File file = (new File(fileName)).getParentFile();
                if (!file.isDirectory()) {
                    file.mkdirs();
                }

                fos = new FileOutputStream(fileName);
                fos.write(content.getBytes());
                rc = true;
            } catch (IOException var13) {
                LOGGER.error("Tools.writeTextFile异常", var13);
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (Exception var12) {
                        LOGGER.error("Tools.writeTextFile异常", var12);
                    }
                }

            }

            return rc;
        } else {
            return false;
        }
    }

    public static String openURL(String url) {
        String rc = "";

        try {
            HttpURLConnection urlConn = (HttpURLConnection)(new URL(url)).openConnection();
            System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

            int len;
            for(char[] buff = new char[1024]; (len = br.read(buff, 0, 1024)) >= 0; rc = rc + new String(buff, 0, len)) {
            }

            br.close();
            urlConn.disconnect();
            if (urlConn.getResponseCode() != 200) {
                rc = "";
            }
        } catch (MalformedURLException var6) {
            rc = "";
            LOGGER.error("Tools.openURL异常", var6);
        } catch (IOException var7) {
            rc = "";
            LOGGER.error("Tools.openURL异常", var7);
        }

        return rc;
    }

    public static int getChineseLength(String str) {
        if (str == null) {
            return 0;
        } else {
            int k = 0;

            for(int i = 0; i < str.length(); ++i) {
                if (str.charAt(i) > 255) {
                    k += 2;
                } else {
                    ++k;
                }
            }

            return k;
        }
    }

    public static String textToHtml(String sourcestr) {
        String restring = "";
        String destr = "";
        int strlen = sourcestr.length();

        for(int i = 0; i < strlen; ++i) {
            char ch = sourcestr.charAt(i);
            switch(ch) {
                case '\r':
                    destr = "<br>";
                    break;
                case ' ':
                    destr = "&nbsp;";
                    break;
                case '"':
                    destr = "";
                    break;
                case '&':
                    destr = "&";
                    break;
                case '<':
                    destr = "<";
                    break;
                case '>':
                    destr = ">";
                    break;
                default:
                    destr = "" + ch;
            }

            restring = restring + destr;
        }

        return "" + restring;
    }

    public static String getWeek() {
        String whatDay = "";
        Calendar c = Calendar.getInstance();
        c.setTime(new java.util.Date(System.currentTimeMillis()));
        int dayOfWeek = c.get(7);
        switch(dayOfWeek) {
            case 1:
                whatDay = "星期日";
                break;
            case 2:
                whatDay = "星期一";
                break;
            case 3:
                whatDay = "星期二";
                break;
            case 4:
                whatDay = "星期三";
                break;
            case 5:
                whatDay = "星期四";
                break;
            case 6:
                whatDay = "星期五";
                break;
            case 7:
                whatDay = "星期六";
        }

        return whatDay;
    }

    public static byte[] getIpByteArrayFromString(String ip) {
        byte[] ret = new byte[4];
        StringTokenizer st = new StringTokenizer(ip, ".");

        try {
            ret[0] = (byte)(Integer.parseInt(st.nextToken()) & 255);
            ret[1] = (byte)(Integer.parseInt(st.nextToken()) & 255);
            ret[2] = (byte)(Integer.parseInt(st.nextToken()) & 255);
            ret[3] = (byte)(Integer.parseInt(st.nextToken()) & 255);
        } catch (Exception var4) {
            LOGGER.error("Tools.getIpByteArrayFromString异常", var4);
        }

        return ret;
    }

    public static String getString(String s, String srcEncoding, String destEncoding) {
        try {
            return new String(s.getBytes(srcEncoding), destEncoding);
        } catch (UnsupportedEncodingException var4) {
            LOGGER.error("Tools.getString异常", var4);
            return s;
        }
    }

    public static String getString(byte[] b, String encoding) {
        try {
            return new String(b, encoding);
        } catch (UnsupportedEncodingException var3) {
            LOGGER.error("Tools.getString异常", var3);
            return new String(b);
        }
    }

    public static String getString(byte[] b, int offset, int len, String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException var5) {
            LOGGER.error("Tools.getString异常", var5);
            return new String(b, offset, len);
        }
    }

    public static String getIpStringFromBytes(byte[] ip) {
        StringBuilder sb = new StringBuilder();
        sb.append(ip[0] & 255);
        sb.append('.');
        sb.append(ip[1] & 255);
        sb.append('.');
        sb.append(ip[2] & 255);
        sb.append('.');
        sb.append(ip[3] & 255);
        return sb.toString();
    }

    public static void clearCache(String url) {
        try {
            Class clazz = InetAddress.class;
            Field cacheField = clazz.getDeclaredField("addressCache");
            cacheField.setAccessible(true);
            Object obj = cacheField.get(clazz);
            Class cacheClazz = obj.getClass();
            Field cachePolicyField = cacheClazz.getDeclaredField("type");
            Field cacheMapField = cacheClazz.getDeclaredField("cache");
            cachePolicyField.setAccessible(true);
            cacheMapField.setAccessible(true);
            Map cacheMap = (Map)cacheMapField.get(obj);
            System.out.println(cacheMap);
            cacheMap.remove(url);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    public static String replaceAllRegex(String str, String oldStr, String newStr) {
        String result = "";
        if (str != null) {
            result = str.replaceAll(oldStr, newStr);
        }

        return result;
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
        return uuid;
    }

    public static String getTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000L);
    }

    public static String build4ForNumberCode() {
        String[] beforeShuffle = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < list.size(); ++i) {
            sb.append(list.get(i));
        }

        String afterShuffle = sb.toString();
        String result = afterShuffle.substring(3, 7);
        return result;
    }

    public static String getRequestParams(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        String paramStr = "";
        Enumeration paramNames = request.getParameterNames();

        while(paramNames.hasMoreElements()) {
            String name = (String)paramNames.nextElement();
            String value = request.getParameter(name);
            params.append(name + "=" + value + "&");
        }

        if (params.length() > 0) {
            paramStr = params.substring(0, params.length() - 1);
        }

        return paramStr;
    }
}
