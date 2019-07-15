package concurrency.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 *
 * @author zz
 */
public class TestNewCachedThreadPool {

    public static void main(String[] args) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        //ExecutorService fixedThreadPool = new ThreadPoolExecutor(10,20,200L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),namedThreadFactory);
        int size = 10;
        ExecutorService fixedThreadPool =
                new ThreadPoolExecutor(size,size,0L,
                        TimeUnit.MILLISECONDS,new SynchronousQueue<Runnable>(),namedThreadFactory);

        for (int i = 1; i <= size; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fixedThreadPool.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("执行：" + index + "，线程名称：" + threadName);
            });
        }
    }



}
