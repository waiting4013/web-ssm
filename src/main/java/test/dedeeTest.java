package test;

import ssm.utils.DateUtil;
import ssm.utils.DateUtils;

import java.sql.Array;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class dedeeTest {

//    public static void main(String[] args) {
        /*try {
            InetAddress ia=InetAddress.getLocalHost();
            String localname=ia.getHostName();
            String localip=ia.getHostAddress();
            System.out.println(localname);
            System.out.println(localip);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/

/*
        List<String> ss=new ArrayList<>();
        ss.add("sda");

        ss.add("ccc");

        ss.add("sss");

        ss.add("ddd");

        ss.add("sxxx");
        teeee teeee=new teeee();
        Map map=null;
        //map.put("cs","sdad");
        System.out.println(map.size());*/

/*        List<String> list=new ArrayList<>();
        Map <String,String>map = new HashMap<>(8);
        map.put("熊大", "棕色");
        map.put("熊二", "黄色");
        String mapKey;
        String mapValue;
        for(Map.Entry<String, String> entry : map.entrySet()){
            mapKey = entry.getKey();
            list.add(mapKey);
            //mapValue = entry.getValue();

        }*/
      /*  Integer s=Integer.parseInt("2233");
        System.out.println(s);
        List<Integer> configNames=new ArrayList<>();
        configNames.add(1);
        configNames.add(1);
*/
        //System.out.println(0%5);
      /*  String ss = "dsca";
        System.out.println(ss.contains("d"));*/

        //yy/MM/dd HH:mm:ss
//        SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(System.currentTimeMillis());
//        String format = df.format(cal.getTime());
//        String OrderDate=format.substring(0,10);
//        String OrderTime=format.substring(11,19);
//        System.out.println(OrderDate);
//        System.out.println(OrderTime);
//        Map dateTimes = getDateTimes();
//        System.out.println("----------"+dateTimes.get("orderDate"));
//        System.out.println("------======--"+dateTimes.get("orderTime"));
//
//    }

    //[{"userDiscount":"1","rule":[{"levelId":1,"vip_level":"V1","levelName":"白银会员","oilPrice92":"1","oilPrice95":"1","oilPrice0":"2","oilPrice98":"3"},{"levelId":2,"vip_level":"V2","levelName":"黄金会员","oilPrice92":"2","oilPrice95":"1","oilPrice0":"2","oilPrice98":"3"},{"levelId":3,"vip_level":"V3","levelName":"铂金会员","oilPrice92":"1","oilPrice95":"1","oilPrice0":"2","oilPrice98":"3"},{"levelId":4,"vip_level":"V4","levelName":"钻石会员","oilPrice92":"1","oilPrice95":"1","oilPrice0":"2","oilPrice98":"3"}]}]


    private static Map getDateTimes() {
        Map<String, String> map = new HashMap<>(2);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        String format = df.format(cal.getTime());
        map.put("orderDate", format.substring(0, 10));
        map.put("orderTime", format.substring(11, 19));
        return map;
    }

    private static void test1111() {
        Timestamp expressStartDateNew = new Timestamp(121231000);
        Timestamp expressEndDateOld = new Timestamp(1212312312);
        Timestamp expressEndDateNew = new Timestamp(1212312312);
        //旧时间
        long oldTime = expressEndDateOld.getTime();
        //新时间
        long newTime = expressEndDateNew.getTime();
        //当前时间
        long nowTime = System.currentTimeMillis();
//        long l = DateUtil.betweenDay(expressStartDateNew, expressEndDateNew, true);
//        System.out.println(l);


    }



    private static void test2() {
        String format = DateUtils.format(new Date(), "yyMMdd-");

        System.out.println(format);
    }

    /**
     * 选择插入
     * @param array
     */
    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;

                }
            }
            if (i != minIndex) {
             int temp = array[i];
             array[i] = array[minIndex];
             array[minIndex] = temp ;
            }
        }
    }

    public static void insertSort(int[] array) {
        for (int i = 1 ; i< array.length ; i ++ ){
            int insertValue = array[i];
            int j = i -1;
            //从右向左比较元素的同时，进行元素复制
            for (; (j>=0) && (insertValue < array[j]); j--){
                array[j+1] = array[j];
            }
            //insertValue的值插入适当位置
            array[j + 1] = insertValue;
        }
    }

    public static void shellSort() {

    }
    public static void main(String[] args) {
        int[] array = new int[] {3,5,16,61,11,25,2,165,12,53,66,9,22};
//        selectionSort(array);
//        System.out.println(Arrays.toString(array));

        insertSort(array);
        System.out.println(Arrays.toString(array));

    }
}
