package test;

public class B extends A{

    @Override
    public void demo1() {
        System.out.println("B");
    }

    public static void main(String[] args) {
        B b = (B) new A();
        b.demo1();
    }
}
