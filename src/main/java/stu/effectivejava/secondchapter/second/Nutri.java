package stu.effectivejava.secondchapter.second;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;

/**
 *
 * buildPattern 建造模式
 * @author
 */
public class Nutri {

    public static  void main(String[] args){

        int[] s=new int[]{5,10,10,1,1};
        int[] w=s.clone();
            s[0]=112;
           String cc= Arrays.toString(s);
        System.out.println(s);
        ThreadPoolTaskExecutor poolTaskExecutor=new ThreadPoolTaskExecutor();

    }


}
