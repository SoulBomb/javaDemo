package com.jihl.supper.thread;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @since 2021/4/28
 */
@Log4j2
@Component
public class ThreadPool {

    public void generatePool() {
        int corePoolSize = 3;
        int maximumPoolSize = 6;
        //超过 corePoolSize 线程数量的线程最大空闲时间
        long keepAliveTime = 2;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(2);
        MyThreadFactory threadFactory = new MyThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = null;
        try {
            threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    unit,
                    workQueue,
                    threadFactory,
                    new ThreadPoolExecutor.AbortPolicy());

            for (int i = 0; i < 8; i++) {
                threadPoolExecutor.submit(MyThread::new);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != threadPoolExecutor) {
                threadPoolExecutor.shutdown();
            }
        }
    }
}
