package stu.designpatterns.factory;

/**
 *
 * @author zz
 *
 */
public class OperateFactory {

    public static Operation createOperate(String operate){
        Operation operation;


        switch (operate)
        {
            default:
                operation=new OperateSub();
                break;
            case "+":
                operation=new OperateAdd();
                break;
            case "-":
                operation=new OperateSub();
                break;

        }
        return operation;
    }
}

