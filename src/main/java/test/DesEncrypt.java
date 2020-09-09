package test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

public class DesEncrypt {
    public static String Key = "eJiAyOu^2014";

    public DesEncrypt() {
    }

    private static byte[] encrypt(String content, String keyWord) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(keyWord.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        byte[] byteContent = content.getBytes("utf-8");
        cipher.init(1, key);
        byte[] result = cipher.doFinal(byteContent);
        return result;
    }

    private static String encrypttoStr(String content, String password) throws Exception {
        return parseByte2HexStr(encrypt(content, password));
    }

    private static byte[] decrypt(byte[] content, String keyWord) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(keyWord.getBytes());
        kgen.init(128, secureRandom);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, key);
        byte[] result = cipher.doFinal(content);
        return result;
    }

    private static byte[] decrypt(String content, String keyWord) throws Exception {
        return decrypt(parseHexStr2Byte(content), keyWord);
    }

    private static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < buf.length; ++i) {
            String hex = Integer.toHexString(buf[i] & 255);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }

            sb.append(hex.toUpperCase());
        }

        return sb.toString();
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for(int i = 0; i < hexStr.length() / 2; ++i) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte)(high * 16 + low);
            }

            return result;
        }
    }

    public static String desString(String inputString) {
        String outputString = "";
        if (inputString != null && !"".equals(inputString)) {
            try {
                outputString = new String(decrypt(inputString, Key));
            } catch (Exception var3) {
                System.out.println("解密失败 " + inputString);
                outputString = inputString;
            }

            return outputString;
        } else {
            return outputString;
        }
    }

    public static String EncString(String inputString) {
        String outputString = "";
        if (inputString != null && !"".equals(inputString)) {
            try {
                outputString = encrypttoStr(inputString, Key);
            } catch (Exception var3) {
                var3.printStackTrace();
                System.out.println("加密失败 " + inputString);
                outputString = inputString;
            }

            return outputString;
        } else {
            return outputString;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("-" + desString("8528CD0C79A7D7C273B623A4AF329084") + "-");
        System.out.println(EncString("13713848629"));
    }
}
