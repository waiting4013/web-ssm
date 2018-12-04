package stu.effectivejava.secondchapter.second;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * buildPattern
 * @author
 */
public class Nutri {

    public static  void main(String[] args){

        int[] s=new int[]{5,10,10,1,1};
        int[] w=s.clone();
            s[0]=112;
           String cc= s.toString();
        System.out.println(s);
        //ThreadPoolTaskExecutor poolTaskExecutor=new ThreadPoolTaskExecutor();

    }


}
