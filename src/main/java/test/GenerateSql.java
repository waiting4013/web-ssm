package test;

public class GenerateSql {

    public static void main(String[] args) {

        String sql ="INSERT INTO ejiayou_xyorder_monitor_quota  (`station_id`, `station_quota`, `state`)  VALUES";

        String [] stationArr= "21175,21127,21387,21283,10211,10243,21265,21267".split(",");
        StringBuilder value= new StringBuilder();
        for (String s : stationArr) {
            value.append(" (").append(s).append(",'0','1'),");
        }
        String finals=sql+value;
        System.out.println(stationArr.length);
        System.out.println(finals);
    }
}
