package test;

import ssm.utils.JsonUtil;

import java.util.*;

public class JsonTest {

    public static void main(String[] args) {

        test1212112();


    }


    private static  void test(){
        List<Map<String,Object>> list = JsonUtil.toList("[{\"qrCode\":\"dq\",\"text\":\"ces\"},{\"qrCode\":\"dq\",\"text\":\"ces\"}]");
        for (Map<String,Object> map:list){
            System.out.println(map);
        }
    }

    private static  void test1212(){
        String executeParam = "1213,41231";
        String[] idArray = executeParam.split(",");
        List<Long> idList = new ArrayList<>();
        for (String s : idArray) {
            idList.add(Long.parseLong(s));
        }
        Long[] idsLongArray = new Long[idList.size()];
        idList.toArray(idsLongArray);
        System.out.println(idList);
        System.out.println(Arrays.asList(idsLongArray));
    }

    private static  void test1212112(){
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

//        Collections.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String a, String b) {
//                return b.compareTo(a);
//            }
//        });
//
//        names.sort((String a, String b) -> {
//            return b.compareTo(a);
//        });

//        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        names.sort((String a, String b) -> b.compareTo(a));
//        names.sort(Comparator.reverseOrder());

        System.out.println(names);
    }

}
