package stu.designpatterns.proxy.dynamicproxy;

public interface IHelloService {
    /**
     * 方法1
     * @param userName
     * @return
     */
    String sayHello(String userName);

    /**
     * 方法2
     * @param userName
     * @return
     */
    String sayByeBye(String userName);
}
