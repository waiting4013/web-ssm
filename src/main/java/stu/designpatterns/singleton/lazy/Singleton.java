package stu.designpatterns.singleton.lazy;
/**
 *
 * 懒汉模式
 */
public class Singleton {

    private static Singleton instance;
    private Singleton (){}

    public static Singleton getInstance() {


        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public  static void main(String[] args){
        String a="121";
        int ad = Integer.parseInt(a);
        StringBuffer ssd=new StringBuffer("aqwqrq");

        StringBuffer as=ssd.reverse();
        as.toString();
        System.out.println(as);
        System.out.println(ad);
    }

}
