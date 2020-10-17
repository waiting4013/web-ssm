package test;

public class GenerateSql {

    public static void main(String[] args) {

        String sql = "INSERT INTO ejiayou_xyorder_monitor_quota  (`station_id`, `station_quota`, `state`)  VALUES";

        String[] stationArr = "21175,21127,21387,21283,10211,10243,21265,21267".split(",");
        StringBuilder value = new StringBuilder();
        for (String s : stationArr) {
            value.append(" (").append(s).append(",'0','1'),");
        }
        String finals = sql + value;
        System.out.println(stationArr.length);
        System.out.println(finals);


        String sql1 = "INSERT INTO  auto_update_oilpirce_station (`station_id`, `oil_id`, `compare_type`,`state`,`is_open`)  VALUES ";
        String[] stationArr1 = ("9953,21363,21361,33,39,21359,114,21357,21449,21491,21079,21069,21005,21165,21387,21171,21129,21133,10691,21283,21153,58,21151,21385,9747,21379,21331,10381,21325,21327,21007,21281,21273,9677,10519,21421,10357,21417,21401,17473,21175,10205,21349,21263,21277,21131,20955,21495,21419,21433,21345,16935,21485,21101,21415,21259,10531,10517,21143,21267,21265,16937,21013,10285,21343,10597,9651,9639,21381,21431,21341,21355,21353,21333,21423,21425,21285,9659,9583,21059,9713,9579,21377,9582,21375,9647,20933,21221,21073,21071,10345,9935,9927,9925,9921,9919,10293,10447,10449,10451,9467,10401,10403,10453,10397,10507,21383,9483,9482,9479,21109,9761,21479,21447,21529,21531,21533,21535,21537,21541,21543,21497,18791,21487,21441,21539,21339,9715,9653,21159,21471,21489,21511,21527,21461,21463,20985,21523,21499,21501,21505,21503,21451,21453,21403,20997,20993,21039,20969,9729,9486").split(",");
        StringBuilder value1 = new StringBuilder();

        for (String s : stationArr1) {
            value1.append(" (").append(s).append(",'1','1','1','1'),");
            value1.append(" (").append(s).append(",'2','1','1','1'),");
            value1.append(" (").append(s).append(",'1','3','1','1'),");
            value1.append(" (").append(s).append(",'2','3','1','1'),");
        }

        String finals1 = sql1 + value1;
        System.out.println(stationArr1.length);
        System.out.println(finals1);
    }
}
