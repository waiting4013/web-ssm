package test;

import org.apache.commons.codec.digest.DigestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class teeee {


    public static void main(String[] args) throws ParseException {
       /* Date DA=minusHours(new Date(),12L);
        String dd=DateUtil.format(DA);
        String bi=DateUtil.getNow();
        String startTime=DateUtil.getTodayNext(-7);
        String endTime=DateUtil.getTodayNext(-1);

        String startTime1= DateUtil.getTodayNext(-7);
        String yesterday= DateUtil.getTodayNext(-1);
        String lastStart= DateUtil.getTodayNext(-14);

        String lastEnd = DateUtil.getTodayNext(-8);*/

        //System.out.println(dd);
      /*  System.out.println(startTime1);
        System.out.println(yesterday);
        System.out.println(lastStart);
        System.out.println(lastEnd);
*/
      /*  Date today = new Date();
        Calendar c=Calendar.getInstance();
        c.setTime(today);
        int weekday=c.get(Calendar.DAY_OF_WEEK)-1;


        System.out.println(DateUtil.getHour(new Date()));
*/

        List<Integer> s = new ArrayList<>();
        s.add(1);
        s.add(2);
        s.add(3);
        s.add(4);

        List<Integer> d = new ArrayList<>();
        d.add(1);
        d.add(2);
        d.add(3);
        d.add(5);
        s.removeAll(d);
        // System.out.println(s);


        String s1 = convertTime("2020-09-01");
        //System.out.println(s1);
        long timestamp = System.currentTimeMillis();
        String sign = DigestUtils.md5Hex("2dK3bN#" + timestamp + "#P3F37w").toUpperCase();
        System.out.println(timestamp);
        System.out.println(sign);
    }

    private static String convertTime(String date) throws ParseException {
        Date format = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        return new SimpleDateFormat("yyyyMM").format(format);
    }

    static Date minusHours(Date date, Long hour) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.minusHours(hour);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
