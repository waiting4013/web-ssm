package stu.designpatterns.factory;

/**
 *
 * @author zz
 */
public abstract class Operation {

    private Integer numA=0;
    private Integer numB=0;

    public Integer getNumA() {
        return numA;
    }

    public void setNumA(Integer numA) {
        this.numA = numA;
    }

    public Integer getNumB() {
        return numB;
    }

    public void setNumB(Integer numB) {
        this.numB = numB;
    }

    /**
     *  运算
     *
     * @return
     */
    public abstract Integer getResult();
}
