package stu.designpatterns.factory;

/**
 * @author zz
 *
 */
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

public class Program {


    public  static  void main(String[] args){

        System.out.println("请输入数字A");

       // String  strNameA=
        Scanner scanner = new Scanner(System.in);
        Integer numA=scanner.nextInt();
        System.out.println("请输入运算符号");
        String operate=scanner.next();
        System.out.println("请输入数字");
        Integer numb=scanner.nextInt();

        Operation operation = OperateFactory.createOperate(operate);
            operation.setNumA(numA);
            operation.setNumB(numb);



        Integer result= Operate.getResult(numA,operate,numb);
        Integer result1=operation.getResult();
        System.out.println("结果是:"+result);
        System.out.println("结果是:"+result1);
    }
}
