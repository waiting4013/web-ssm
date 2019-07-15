package stu.designpatterns.proxy.staticproxy;

public class MainStatic {

        public static void main(String[] args) {
            StaticProxyHello staticProxyHello = new StaticProxyHello();
            staticProxyHello.sayHello("测试");
        }

}
