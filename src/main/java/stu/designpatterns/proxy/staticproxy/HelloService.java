package stu.designpatterns.proxy.staticproxy;

public class HelloService implements IHelloService {

    @Override
    public String sayHello(String userName) {

        System.out.println("helloService" + userName);
        return "HelloService" + userName;
    }
}
