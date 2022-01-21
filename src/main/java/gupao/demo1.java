package gupao;

import java.util.ArrayList;
import java.util.List;

public class demo1 {
    public static void main(String[] args) {
        //app ClassLoader
//        System.out.println(new Tom().getClass().getClassLoader());
//
//        // Ext ClassLoader
//        System.out.println(new Tom().getClass().getClassLoader().getParent());
//        //Bootstrap ClassLoader
//        System.out.println(new Tom().getClass().getClassLoader().getParent().getParent());
//
//        System.out.println(new String().getClass().getClassLoader());


        List<String> aaa = new ArrayList<>();
        aaa.add("asd");
        aaa.add("qwe");
        aaa.add("ert");
        aaa.add("yty");
        List<String> bbb = new ArrayList<>();
        aaa.addAll(bbb);
        System.out.println(aaa);
    }
}
