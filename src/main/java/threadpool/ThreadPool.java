package threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    private static final int CORE_POOL_SIZE = 50;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;
    private  static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY),new ThreadPoolExecutor.CallerRunsPolicy());
    public static void main(String[] args) {

        // 向线程池提交任务
        for (int i = 0; i < THREAD_POOL.getCorePoolSize(); i++) {
            THREAD_POOL.execute(() -> {
                for (int x = 0; x < 2; x++) {
                    System.out.println(Thread.currentThread().getName() + ":" + x);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        // 关闭线程池
        THREAD_POOL.shutdown(); // 设置线程池的状态为SHUTDOWN，然后中断所有没有正在执行任务的线程
        // threadPool.shutdownNow(); // 设置线程池的状态为STOP，然后尝试停止所有的正在执行或暂停任务的线程，并返回等待执行任务的列表，该方法要慎用，容易造成不可控的后果
    }
}
