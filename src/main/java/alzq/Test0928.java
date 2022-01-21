package alzq;

import ssm.utils.JsonUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author li
 */
public class Test0928 {


    public static void main(String[] args) {
        //增幅
        //1.428571428571429
        //金额
        BigDecimal ben = BigDecimal.valueOf(60);
        //利率
        BigDecimal rate = BigDecimal.valueOf(0.25);
        //年限
        int year = 5;
        //工资
        BigDecimal sa = BigDecimal.valueOf(10);
        BigDecimal odd = cal(year, ben, rate, sa);
        System.out.println(odd);
    }

    //50    34.3 + 8 + 0.4 + 12 + 1 + 2.8 + 1 + 0.5


    private static BigDecimal cal(int year, BigDecimal ben, BigDecimal rate, BigDecimal sa) {
        BigDecimal finalMoney = ben;
        for (int i = 0; i < year; i++) {
            finalMoney = finalMoney.add(finalMoney.multiply(rate)).add(sa);
        }
        return finalMoney;
        //.add(BigDecimal.valueOf(8));
    }

//[{"carType":"5","icon":"https://img.ejiayou.com/upload/2020/8/11e83a0b-af7c-4a07-88ce-dc173110ca1c-1597905137811.png","adIcon":"https://img.ejiayou.com/upload/2020/8/954386ef-e2f7-4270-b591-40661a0fb6ec-1598854678006.png"},{"carType":"3","icon":"https://img.ejiayou.com/upload/2020/8/c4c96632-65ca-4453-aac8-3a743754de27-1597905168543.png","adIcon":"https://img.ejiayou.com/upload/2020/8/e22ac4f3-71b5-4e7c-9de0-6411fa7d274e-1598080164218.png"},{"carType":"1","icon":"https://img.ejiayou.com/upload/2020/8/e6a3278d-efe4-48a3-997c-67467a8b191e-1597905193709.png","adIcon":"https://img.ejiayou.com/upload/2020/8/e22ac4f3-71b5-4e7c-9de0-6411fa7d274e-1598080164218.png"},{"carType":"800","icon":"https://img.ejiayou.com/upload/2020/8/27ab043e-cef9-499c-ac11-87c378f4f284-1597905237654.png","adIcon":"https://img.ejiayou.com/upload/2020/8/e22ac4f3-71b5-4e7c-9de0-6411fa7d274e-1598080164218.png"}]
    private static  void test(){
        List<Map<String,Object>> list = JsonUtil.toList("[{\"qrCode\":\"dq\",\"text\":\"ces\"},{\"qrCode\":\"dq\",\"text\":\"ces\"}]");
        for (Map<String,Object> map:list){
            System.out.println(map);
        }
    }
}
