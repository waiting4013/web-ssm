package newinterview;

import java.math.BigDecimal;

public class Test0928 {


    public static void main(String[] args) {
        //金额
        BigDecimal ben = BigDecimal.valueOf(100);
        //利率
        BigDecimal rate = BigDecimal.valueOf(0.20);
        //年限
        int year = 5;
        BigDecimal odd = cal(year, ben, rate);
        System.out.println(odd);
    }

    //50    34.3 + 8 + 0.4 + 12 + 1 + 2.8 + 1 + 0.5


    private static BigDecimal cal(int year, BigDecimal ben, BigDecimal rate) {
        BigDecimal finalMoney = ben;
        for (int i = 0; i < year; i++) {
            finalMoney = finalMoney.add(finalMoney.multiply(rate));
        }
        return finalMoney;
    }
}
