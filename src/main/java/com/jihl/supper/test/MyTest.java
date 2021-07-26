package com.jihl.supper.test;

import com.jihl.supper.SupperApplication;
import com.jihl.supper.bean.Iron;
import com.jihl.supper.bean.Spider;
import com.jihl.supper.schedul.CronTaskRegistrar;
import com.jihl.supper.schedul.SchedulingRunnable;
import com.jihl.supper.thread.ThreadPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author admin
 * @since 2021/4/29
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupperApplication.class)
public class MyTest {

    @Autowired
    private ThreadPool threadPool;

    @Autowired
    CronTaskRegistrar cronTaskRegistrar;

    @Test
    public void cC() {
        Spider spider = new Spider();
        Iron iron = new Iron().setSpeed("12");
        iron.setSupper("hard");
        BeanUtils.copyProperties(iron, spider);
        System.out.println(spider);
    }

    @Test
    public void testThread() {
        threadPool.generatePool();
        System.out.println(1);
    }

    @Test
    public void testTask() throws InterruptedException {
        SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskNoParams", (Object) null);
        cronTaskRegistrar.addCronTask(task, "0/10 * * * * ?");

        // 便于观察
        Thread.sleep(3000000);
    }

    @Test
    public void testHaveParamsTask() throws InterruptedException {
        SchedulingRunnable task = new SchedulingRunnable("demoTask", "taskWithParams", "haha", 23);
        cronTaskRegistrar.addCronTask(task, "0/10 * * * * ?");

        // 便于观察
        Thread.sleep(3000000);
    }
}
