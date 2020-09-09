package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class dedeeTest {

    public static void main(String[] args) {
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
        SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        String format = df.format(cal.getTime());
        String OrderDate=format.substring(0,10);
        String OrderTime=format.substring(11,19);
        System.out.println(OrderDate);
        System.out.println(OrderTime);
        Map dateTimes = getDateTimes();
        System.out.println("----------"+dateTimes.get("orderDate"));
        System.out.println("------======--"+dateTimes.get("orderTime"));

    }

    //[{"userDiscount":"1","rule":[{"levelId":1,"vip_level":"V1","levelName":"白银会员","oilPrice92":"1","oilPrice95":"1","oilPrice0":"2","oilPrice98":"3"},{"levelId":2,"vip_level":"V2","levelName":"黄金会员","oilPrice92":"2","oilPrice95":"1","oilPrice0":"2","oilPrice98":"3"},{"levelId":3,"vip_level":"V3","levelName":"铂金会员","oilPrice92":"1","oilPrice95":"1","oilPrice0":"2","oilPrice98":"3"},{"levelId":4,"vip_level":"V4","levelName":"钻石会员","oilPrice92":"1","oilPrice95":"1","oilPrice0":"2","oilPrice98":"3"}]}]



    private static Map  getDateTimes(){
        Map<String,String> map=new HashMap<>(2);
        SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        String format = df.format(cal.getTime());
        map.put("orderDate",format.substring(0,10));
        map.put("orderTime",format.substring(11,19));
        return map;
    }

}
