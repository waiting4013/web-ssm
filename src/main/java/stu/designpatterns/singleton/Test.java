package stu.designpatterns.singleton;

public class Test {

    public void test(){
        Singleton.getInstance().readResolve();


    }
}
