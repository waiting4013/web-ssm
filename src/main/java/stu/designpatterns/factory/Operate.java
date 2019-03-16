package stu.designpatterns.factory;

/**
 *  计算器的后台
 * @author zz
 */
public class Operate {

    public static Integer getResult(Integer numA,String operate,Integer numB){
        Integer result;

        switch (operate){
            case "+":
                result=numA+numB;
                break;
            case "-":
                result=numA-numB;
                break;
            default:
                result=numA+numB;
                break;

        }

        return result;
    }
}
