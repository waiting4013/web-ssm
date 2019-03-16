package stu.designpatterns.factory;
/**
 * @author zz
 *
 */
public class OperateSub extends Operation {
    @Override
    public Integer getResult() {
        Integer result;
        result=getNumA()-getNumB();
        return result;
    }
}
