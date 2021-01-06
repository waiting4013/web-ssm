package test;

import ssm.utils.DateUtils;

public class TimeTest {
    public static void main(String[] args) {

        deal();
    }
    private static void deal(){
        String beforeDays = DateUtils.getBeforeDays("2020-11-11", -7);
        System.out.println(beforeDays);
    }
}
