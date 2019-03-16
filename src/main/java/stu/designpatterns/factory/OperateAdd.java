package stu.designpatterns.factory;

/**
 * @author zz
 *
 */
public class OperateAdd extends Operation{


    @Override
    public Integer getResult() {
        Integer result=super.getNumA()+super.getNumB();

        return result;
    }
}
