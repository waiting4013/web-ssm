package threadpool;

import java.util.Date;

public class MyRunnable implements Runnable {
    private String command;

    public MyRunnable(String s) {
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand(command);
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }

    private void processCommand(String temp) {
        try {
            Thread.sleep(5000);
            System.out.println("task-----------------" + temp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
