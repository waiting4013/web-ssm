package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.*;

public class A {

    public void demo1(){
        System.out.println("A");
    }
    //有个需求帮个忙撒，比如我23年存20元，24年存20元，
    // 25年存10元，总余额50元，但是我要用45，
    // 需要把23年扣掉，24年扣掉，
    // 25年扣5元，怎么设计呀。。。
    public static void main(String[] args) {

        String dates = "[{\"skipCycleStartTime\":\"2023-12-11 12:00:00\",\"skipCycleEndTime\":\"2023-12-13 12:00:00\"},{\"skipCycleStartTime\":\"2023-12-26 12:00:00\",\"skipCycleEndTime\":\"2023-12-29 12:00:00\"}]";
        List<JSONObject> jsonObject = JSON.parseObject(dates, List.class);

        int ods = 45632;
        int split = ods/10000;
        //每次查询一万条
        List<Integer> integers = new ArrayList<>();
        for (int s = 0; s <= split; s++) {
            Integer temp = 0;
            temp =  s * 10000;
            integers.add(temp);
        }
        Map<String,List<Integer>> dataList = new HashMap<>();
        Integer temp = 0;
        for (Integer integer : integers) {


            temp ++ ;
            List<Integer> jobInfoList = Arrays.asList(temp,2,3);
            dataList.put("data" + temp , jobInfoList);
        }
        int e = 0;
        for (Integer integer : integers) {
            e ++;
            System.out.println(dataList.get("data" + e));
        }
        System.out.println(dataList);
    }
}
