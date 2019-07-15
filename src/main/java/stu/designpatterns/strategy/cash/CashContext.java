package stu.designpatterns.strategy.cash;

public class CashContext {
     CashSuper cs;

    public CashContext(String type){
        switch (type){
            default:
                CashNormal cs0=new CashNormal();
                cs=cs0;
            case "正常收费":
                CashNormal cs1=new CashNormal();
                cs=cs1;
                break;
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

    }

    public double getResult(double money){

        return cs.acceptCash(money);
    }
}
