package test;


import java.util.Random;

public class CalendarTest {
    public static void main(String[] args) {

      /*  String firstDay= DateUtil.getMonthFirstDay(0);
        String finallDay=DateUtil.getMonthFinalDay(0);

        System.out.println(firstDay);
        System.out.println(finallDay);
        // 获取当前年份、月份、日期
        Calendar cale = Calendar.getInstance();
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        int hour = cale.get(Calendar.HOUR_OF_DAY);
        int minute = cale.get(Calendar.MINUTE);
        int second = cale.get(Calendar.SECOND);
        int dow = cale.get(Calendar.DAY_OF_WEEK);
        int dom = cale.get(Calendar.DAY_OF_MONTH);
        int doy = cale.get(Calendar.DAY_OF_YEAR);

    *//*    System.out.println("Current Date: " + cale.getTime());
        System.out.println("Year: " + year);
        System.out.println("Month: " + month);
        System.out.println("Day: " + day);
        System.out.println("Hour: " + hour);
        System.out.println("Minute: " + minute);
        System.out.println("Second: " + second);
        System.out.println("Day of Week: " + dow);
        System.out.println("Day of Month: " + dom);
        System.out.println("Day of Year: " + doy);*//*

        // 获取当月第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday, lastday;
        // 获取前月的第一天
        LocalDateTime dt = LocalDateTime.now();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        // 获取前月的最后一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
        System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);

        // 获取当前日期字符串
        Date d = new Date();
        System.out.println("当前日期字符串1：" + format.format(d));
        System.out.println("当前日期字符串2：" + year + "/" + month + "/" + day + " "
                + hour + ":" + minute + ":" + second);*/
    /*    String lastMonthStartTime=DateUtil.getMonthFirstDay(-1)+" 00:00:00";

        String lastMonthEndTime=DateUtil.getMonthFinalDay(-1)+" 00:00:00";

        String month=DateUtil.getMonth(0);
        String newMonth=month.substring(4);
        BigDecimal one=BigDecimal.ZERO;
        BigDecimal WTO=BigDecimal.ZERO;
        Integer num=WTO.compareTo(one);

        System.out.println(num);
        //System.out.println(lastMonthEndTime);*/
/*
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTimes=LocalDateTime.now().minusDays(7);
        String localDateTime=LocalDateTime.now().format(dtf2);
        String localDateTime1= LocalDateTime.now().minusDays(7).format(dtf2);
        String s=DateUtil.getNow();
        Date e=DateUtil.getLastDayOfWeek(new Date());
        System.out.println(s);
        System.out.println(localDateTime);
        System.out.println(localDateTime1);


        String time = DateUtil.formatDateTime(System.currentTimeMillis(), 1);
        System.out.println(time);
*/


        //Math.random();
        Random rand = new Random();
        //[900]：900个    100：从100
        int x = rand.nextInt(900) + 100;
        System.out.println(x);

    }


}
