package stu.passobject;



public class PassObject {

    static  void f(Letter y){
        y.c='z';
        y.s=12;
    }



    public static  void main(String[] args){


       /* Letter x=new Letter();
        x.c='a';
        x.s=32;
       System.out.println("1: x.c: "+  x.c);
        System.out.println("1: x.s: "+  x.s);
        f(x);
       System.out.println("1: x.c: "  + x.c);
        System.out.println("1: x.s: "  + x.s);*/
       String s="1cesdqasdaf";
       String a=s.substring(0,2);
        String b=s.substring(2,s.length());
        System.out.println(a);
        System.out.println(b);
    }


}

