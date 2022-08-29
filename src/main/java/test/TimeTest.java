package test;

import com.xiaoleilu.hutool.date.DateUnit;
import com.xiaoleilu.hutool.date.DateUtil;
import ssm.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {

    private static void deal() {
        String beforeDays = DateUtils.getBeforeDays("2020-07-01", 30);
        String after = DateUtils.getTodayNext(30);
        System.out.println(beforeDays);
        System.out.println(after);

        long p = 89;
        System.out.println(p / 30);
        //3*30
        String format = DateUtils.format(new Date(), "yyyy-MM-dd");
        System.out.println(format);
        long l = System.currentTimeMillis();
        Date date = DateUtils.timestampToDate(l);
        System.out.println(date);
        String todayNext = DateUtils.getTodayNext(0);
        System.out.println(todayNext);
//        DateUtil.between()
    }

    private static void test() {
        int settle = 30;
        //结算周期  1月结  2季结
//        if (1 == assetLeaseOrderDetail.getSettlementCycle()){
//            settle = 30;
//        }else {
//            settle = 90;
//        }
        String todayNext = DateUtils.getTodayNext(0);
        String startDateString = "2022-06-01";
        String endDateString = "2022-06-01";
        Date leaseCycleStartDate = DateUtils.formatDate(startDateString);
        Date endDateDate = DateUtils.formatDate(endDateString);
        long between = DateUtil.between(leaseCycleStartDate, endDateDate, DateUnit.DAY) + 1;
        System.out.println(between);
        Long cycle = between / 30;
        System.out.println(cycle);
        int newStart = cycle.intValue() + 1;
        //下期开始时间
        String beforeDays = DateUtils.getBeforeDays(startDateString, newStart * settle);
        String payStart = DateUtils.getBeforeDays(startDateString, cycle.intValue() * settle);
        String payEnd = DateUtils.getBeforeDays(startDateString, newStart * settle - 1);
        int bill = newStart + 1;
        String beforeend = DateUtils.getBeforeDays(startDateString, bill * settle - 1);

        String billDate = DateUtils.getBeforeDays(startDateString, bill * settle - 1);
        System.out.println("账单开始时间:" + payStart);
        System.out.println("账单结束时间:" + payEnd);
        System.out.println("下期开始时间:" + beforeDays);
        System.out.println("下期开始结束时间:" + beforeend);
        System.out.println("应付日期:" + billDate);
//        那么账单的开始时间和结束时间就是
        if (beforeDays.equals(todayNext)) {
            //生成应付明细

        }
    }

    public static void main(String[] args) {

        test1();
    }

    private static void test1() {
        String startDateString = "2022-06-04";
        Date leaseCycleStartDate = DateUtils.parse(startDateString, DateUtils.FORMAT_SHORT);
        String endDateString = "2022-07-05";

        String now = "2022-07-04";
        Date nowDate = DateUtils.parse(now, DateUtils.FORMAT_SHORT);
        String today = DateUtils.getTodayNext(0);
        int settle;
        //结算周期  1月结  2季结
        if (1 == 1) {
            settle = 30;
        } else {
            settle = 90;
        }
        //当前时间和租赁开始时间 期间有多少天
        long betweenDay = DateUtil.between(leaseCycleStartDate, nowDate, DateUnit.DAY) + 1;
        // 已经生成的期数
        Long cycle;
        long nowBetween;
        //账单开始时间
        String payStart;
        //账单开始时间 date 类型
        Date payStartDate;
        int newStart;
        int bill;
        if (endDateString.equals(now)) {
            cycle = betweenDay / settle;
            //账单开始时间
            payStart = DateUtils.getBeforeDays(startDateString, cycle.intValue() * settle);
            //账单开始时间 date 类型
            payStartDate = DateUtils.parse(payStart, DateUtils.FORMAT_SHORT);
            //账单开始时间 和 当前时间 期间有多少天
            nowBetween = DateUtil.between(payStartDate, nowDate, DateUnit.DAY) + 1;
            newStart = cycle.intValue() + 1;
            bill = newStart ;

        } else {
            cycle = betweenDay / settle - 1;
            //账单开始时间
            payStart = DateUtils.getBeforeDays(startDateString, cycle.intValue() * settle);
            //账单开始时间 date 类型
            payStartDate = DateUtils.parse(payStart, DateUtils.FORMAT_SHORT);
            //账单开始时间 和 当前时间 期间有多少天
            nowBetween = DateUtil.between(payStartDate, nowDate, DateUnit.DAY);

            newStart = cycle.intValue() + 1;

            bill = newStart + 1;


        }


        //应付日期 生成应付明细的这个周期的最后一天
        String billDate = DateUtils.getBeforeDays(startDateString, bill * settle - 1);
        //应付日期 date类型
        Date billDates = DateUtils.parse(billDate, DateUtils.FORMAT_SHORT);
        String nextCycleStart = DateUtils.getBeforeDays(startDateString, newStart * settle);
        if (endDateString.equals(now)) {
            String payEnd = now;
            //应付日期
            //生成日期
            System.out.println("账单开始时间a:" + payStart);
            System.out.println("账单结束时间a:" + payEnd);
            System.out.println("间隔天数a" + nowBetween);
            System.out.println("应付日期a:" + billDate);
        } else {
            //当前时间不等于 结束时间
            String payEnd = DateUtils.getBeforeDays(startDateString, newStart * settle - 1);
            //下个周期开始时间
            System.out.println("账单开始时间b:" + payStart);
            System.out.println("账单结束时间b:" + payEnd);
            System.out.println("下期开始时间b:" + nextCycleStart);
            System.out.println("间隔天数b" + nowBetween);
            System.out.println("应付日期b:" + billDate);
            //生成应付明细  当前时间等于下个周期开始时间 生成上一期的应付明细
            if (nextCycleStart.equals(now)) {
                System.out.println("账单开始时间c:" + payStart);
                System.out.println("账单结束时间c:" + payEnd);
                System.out.println("下期开始时间c:" + nextCycleStart);
                System.out.println("间隔天数c" + nowBetween);
                System.out.println("应付日期c:" + billDate);
            }
        }


    }
}
