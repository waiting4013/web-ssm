package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SnCode {


    public static void main(String[] args) {
        String snCode = "11111111111111111111";
        String regSn = "^[A-Za-z0-9]{1,20}$";
        test1(snCode, regSn);
        snCode = "2E:6D:C1:FB:C6:BF";
        regSn = "^[A-Fa-f\\d]{2}:[A-Fa-f\\d]{2}:[A-Fa-f\\d]{2}:[A-Fa-f\\d]{2}:[A-Fa-f\\d]{2}:[A-Fa-f\\d]{2}$";
        test1(snCode, regSn);

        snCode = "司机打";
        regSn = "[\u4e00-\u9fa5]";
        test1(snCode, regSn);
    }


    public static void test1(String code, String xxxx){
        Pattern pattern=Pattern.compile(xxxx);
        Matcher matcher = pattern.matcher(code);
        System.out.println(matcher.find());
        System.out.println(matcher.matches());
    }
}
