package aop.bean;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CarTest {
    public  void  carTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CarConfig.class);
        //根据ID从容器容获取bean
        Car car = (Car) context.getBean("chenbenbuyi");
        car.drive();
    }
}
