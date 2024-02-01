package stream;

import lombok.Data;
import ssm.utils.DateUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author 597059
 */
@Data
public class Person {
    /**
     * 姓名
     */
    private String name;
    /**
     * 薪资
     */
    private int salary;
    /**
     * 年龄
     */
    private int age;
    /**
     * 性别
     */
    private String sex;
    /**
     * 地区
     */
    private String area;

    public Person(String name, int salary, int age, String sex, String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    public static void main(String[] args) {

        long until = 12000;
        int second = 12%60;
        int min = 12*60%60;
        int hour = 12*60*60%60;
        String dater= hour+ ":" + min+ ":"+second;
        Date date = DateUtil.strToDate(dater, DateUtil.TIME);
        String format = DateUtil.format(date, DateUtil.TIME);
        System.out.println(format);
        System.out.println( hour+ ":" + min+ ":"+second);
//        Data
        String delaySecond = LocalDateTime.ofInstant(Instant.ofEpochSecond(until), ZoneOffset.of("+8")).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(delaySecond);
    }

   static void  tsetTime() {
        LocalDateTime sarTime = LocalDateTime.parse("2022-11-16T11:37:12");
        LocalDateTime endTime = LocalDateTime.parse("2022-12-26T23:15:30");
        System.out.println("开始时间" + sarTime);
        System.out.println("结束时间" + endTime);
        int val = endTime.compareTo(sarTime);
        if (val > 0) {
            System.out.println("结束时间大于开始时间");
        } else if (val < 0) {
            System.out.println("结束时间小于开始时间");
        } else {
            System.out.println("结束时间等于开始时间");
        }

    }
}
