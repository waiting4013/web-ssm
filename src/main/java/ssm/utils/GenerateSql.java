package ssm.utils;

import java.util.Calendar;
import java.util.Date;

public class GenerateSql {

    public static void main(String[] args) {
        String stat=    "";
//        String SSD1=stat.replaceAll("\\n",",");
        String SSD1 = "G15431637_1,G15431637_2,G15431637_4,G15431637_5,G15431637_6,G15431637_8,G15431637_9,G15431637_10,G15431637_13,G15431637_3,G15431637_7,G15431637_11,G15431637_12";
        System.out.println(SSD1);
        //String sql = "INSERT INTO ejiayou_xyorder_monitor_quota  (`station_id`, `station_quota`, `state`)  VALUES";

        String[] stationArr = SSD1.split(",");
        StringBuilder value = new StringBuilder();
        for (String s : stationArr) {
            value.append("'").append(s).append("',");
        }
        //String finals = sql + value;
        System.out.println(value);
        //System.out.println(finals);
        String sqq="22127";
        //
//        String SSD=sqq.replaceAll("\\n",",");
        String SSD="22167,22183,22187,22185,22161,22181,22171,22165,22159,22137,22139";
        //System.out.println(SSD);
        String[] stationArr1=SSD.split(",");

        //System.out.println(SSD);
        String sql1 = "INSERT INTO  auto_update_oilpirce_station (`station_id`, `oil_id`, `compare_type`,`state`,`is_open`,`is_commission`)  VALUES ";
        StringBuilder value1 = new StringBuilder();

        for (String s : stationArr1) {
            value1.append(" (").append(s).append(",'1','1','1','1','0'),");
            value1.append(" (").append(s).append(",'2','1','1','1','0'),");
            value1.append(" (").append(s).append(",'1','3','1','1','0'),");
            value1.append(" (").append(s).append(",'2','3','1','1','0'),");
        }

        String finals1 = sql1 + value1;
        //System.out.println(stationArr1.length);
        System.out.println(finals1);

        Date onLineTime = DateUtils.parse("2020-01-01 00:00:09", "yyyy-MM-dd HH:mm:ss");
        Date cooperationTime = DateUtils.parse("2021-01-21 00:00:09", "yyyy-MM-dd HH:mm:ss");
        long day = 24 * 3600 * 1000;

        long startTime = onLineTime.getTime();
        long endTime = cooperationTime.getTime();

        long l = DateUtils.dateDiff(DateUtils.DiffType.Year, cooperationTime, onLineTime);
/*        System.out.println(startTime);
        System.out.println(endTime);*/

        Boolean aBoolean = dateCompare(onLineTime, cooperationTime, 1);
        System.out.println(aBoolean);

        long l1 = System.currentTimeMillis();
        String.valueOf(l1).lastIndexOf(5);
        System.out.println(String.valueOf(l1));
    }
    public static Boolean dateCompare(Date time1,Date time2,int numYear) {
        Date time3 = add(time1, Calendar.YEAR,numYear);
        return time3.getTime() < time2.getTime();
    }
    /**
     * 时间加减
     * @param date
     * @param calendarField ：Calendar.YEAR/ Calendar.MONTH /Calendar.DAY
     * @param amount
     * @return
     */
    public static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            return null;
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    }
