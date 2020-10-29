package test;

import ssm.utils.JsonUtil;

import java.util.List;
import java.util.Map;

public class JsonTest {

    public static void main(String[] args) {

        test();


    }


    private static  void test(){
        List<Map<String,Object>> list = JsonUtil.toList("[{\"qrCode\":\"dq\",\"text\":\"ces\"},{\"qrCode\":\"dq\",\"text\":\"ces\"}]");
        for (Map<String,Object> map:list){
            System.out.println(map);
        }
    }
}
