package ssm.utils;

public class GenerateSql {

    public static void main(String[] args) {
        String stat= "5999\n" +
                "6001\n" +
                "6003\n" +
                "6005\n" +
                "6007\n" +
                "6009\n" +
                "6011\n" +
                "6013\n" +
                "6015\n" +
                "6017\n" +
                "6019\n" +
                "6021\n" +
                "6023\n" +
                "6025\n" +
                "6027\n" +
                "6029\n" +
                "6031\n" +
                "6033\n" +
                "6035\n" +
                "6037\n" +
                "6039\n" +
                "6041\n" +
                "6043\n" +
                "6045\n" +
                "6047\n" +
                "6049\n" +
                "6051\n" +
                "6053\n" +
                "6063\n" +
                "6065\n" +
                "6067\n" +
                "6069\n" +
                "6071\n" +
                "6073\n" +
                "6075\n" +
                "6077";
        String SSD1=stat.replaceAll("\\n",",");
        System.out.println(SSD1);
        //String sql = "INSERT INTO ejiayou_xyorder_monitor_quota  (`station_id`, `station_quota`, `state`)  VALUES";

        String[] stationArr = SSD1.split(",");
        StringBuilder value = new StringBuilder();
        for (String s : stationArr) {
            value.append(" (").append(s).append(",'0','1'),");
        }
        //String finals = sql + value;
        System.out.println(stationArr.length);
        //System.out.println(finals);
        String sqq="23";
        //
        String SSD=stat.replaceAll("\\n",",");
        String[] stationArr1=SSD.split(",");

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
