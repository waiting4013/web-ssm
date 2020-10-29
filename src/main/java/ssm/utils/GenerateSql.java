package ssm.utils;

public class GenerateSql {

    public static void main(String[] args) {
        String stat="21009\n" +
                "21485\n" +
                "20985\n" +
                "20955\n" +
                "10597\n" +
                "10531\n" +
                "10517\n" +
                "11889\n" +
                "11951\n" +
                "11953\n" +
                "9645\n" +
                "10677\n" +
                "10679\n" +
                "21543\n" +
                "9795\n" +
                "2\n" +
                "9484\n" +
                "21179\n" +
                "21465\n" +
                "21277\n" +
                "10211\n" +
                "21503\n" +
                "21451\n" +
                "21453\n" +
                "21507\n" +
                "21443";
        String SSD1=stat.replaceAll("\\n",",");

        String sql = "INSERT INTO ejiayou_xyorder_monitor_quota  (`station_id`, `station_quota`, `state`)  VALUES";

        String[] stationArr = SSD1.split(",");
        StringBuilder value = new StringBuilder();
        for (String s : stationArr) {
            value.append(" (").append(s).append(",'0','1'),");
        }
        String finals = sql + value;
        System.out.println(stationArr.length);
        System.out.println(finals);
        String sqq="23";
        String SSD=sqq.replaceAll("\\n",",");
        String[] stationArr1={"12"};

        //System.out.println(SSD);
        String sql1 = "INSERT INTO  auto_update_oilpirce_station (`station_id`, `oil_id`, `compare_type`,`state`,`is_open`)  VALUES ";
        StringBuilder value1 = new StringBuilder();

        for (String s : stationArr1) {
            value1.append(" (").append(s).append(",'1','1','1','1'),");
            value1.append(" (").append(s).append(",'2','1','1','1'),");
            value1.append(" (").append(s).append(",'1','3','1','1'),");
            value1.append(" (").append(s).append(",'2','3','1','1'),");
        }

        String finals1 = sql1 + value1;
        //System.out.println(stationArr1.length);
        System.out.println(finals1);
    }
}
