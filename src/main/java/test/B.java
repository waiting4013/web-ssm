package test;

import ssm.utils.DateUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;

public class B extends A{

    @Override
    public void demo1() {
        System.out.println("B");
    }

    public static void main(String[] args) {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDateTime last = LocalDateTime.of(2023,12,28,14,29,30);
//
//        long until = LocalDateTime.now().until(last, ChronoUnit.SECONDS);
//        long until2 = last.until(now, ChronoUnit.SECONDS);
//        String delaySecond = DateUtils.revertSecondToDateString(until2);
//
//        int val = now.compareTo(last);

//        String pattern = "yyyy-MM-dd HH:mm:ss";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//        LocalDateTime localDateTime = LocalDateTime.parse("2023-12-11 12:12:12", formatter);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("timeType", null);
//        String 对对对 = (String)map.get("timeType");

        String test = "121212@dqwdq";
        String[] split = test.split("@");
        System.out.println(split);
//        System.out.println(localDateTime);
//        System.out.println(val);
//        System.out.println(until);
//        System.out.println(until2);
//        System.out.println(delaySecond);
        Date date = DateUtils.addDays(new Date(), -2);
        String format = DateUtils.format(date, DateUtils.FORMAT_SHORT_DD);
        Date format1 = DateUtils.format(format, DateUtils.FORMAT_SHORT_DD);

        System.out.println(date);
        System.out.println(format);
        System.out.println(format1);
    }
}
