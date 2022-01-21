package test;

import ssm.utils.DateUtils;

import java.math.BigDecimal;
import java.util.List;

public class MaoPaoTest {
    public static void main(String[] args) {

        String stat="22167\n" +
                "22183\n" +
                "22187\n" +
                "22185\n" +
                "22161\n" +
                "22181\n" +
                "22171\n" +
                "22165\n" +
                "22159\n" +
                "22137\n" +
                "22139\n" +
                "22131\n" +
                "22097\n" +
                "22011\n" +
                "22005\n" +
                "21949\n" +
                "21947\n" +
                "21897\n" +
                "21867\n" +
                "21877\n" +
                "21861\n" +
                "21813\n" +
                "21613\n" +
                "21699\n" +
                "21611\n" +
                "21615\n" +
                "21607\n" +
                "21593\n" +
                "21519\n" +
                "21515\n" +
                "21467\n" +
                "21469";

        String SSD1=stat.replaceAll("\\n",",");
        System.out.println(SSD1);
        String[] stationArr = SSD1.split(",");
        String[] stationArr2 = SSD1.split(",");
        for (String sr : stationArr) {
            for (String s : stationArr2) {
                BigDecimal add = Tools.stringToBigDecimal(sr).add(Tools.stringToBigDecimal(s));
                if(add.toString().equals("-1174.46")){
                    //System.out.println(sr+"+"+s);
                }
            }
        }


        String monthFirst = DateUtils.getMonthFirstDay(0);
        String monthFinal = DateUtils.getMonthFinalDay(0);
        List<String> daysStr = DateUtils.findDaysStr(monthFirst, monthFinal);
        System.out.println(daysStr.size());

        //System.out.println(SSD1);
    }



}
