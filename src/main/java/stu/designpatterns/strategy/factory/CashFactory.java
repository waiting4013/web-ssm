package stu.designpatterns.strategy.factory;

import stu.designpatterns.strategy.cash.CashNormal;
import stu.designpatterns.strategy.cash.CashRebate;
import stu.designpatterns.strategy.cash.CashReturn;
import stu.designpatterns.strategy.cash.CashSuper;

/**
 *
 *@author zz
 *
 *
 */
public class CashFactory {

        public static CashSuper createCashAccept(String type){
            CashSuper cs=null;
            switch (type){
                case "正常收费":
                    cs=new CashNormal();
                    break;

                default:
                    cs=new CashNormal();
                case "满300返100":
                    CashReturn cr1=
                    new CashReturn("300","200");
                    cs=cr1;
                    break;
                case "打八折":
                    CashRebate cr2=
                            new CashRebate("0.8");
                    cs=cr2;
                    break;
            }
            return cs;
        }

}
