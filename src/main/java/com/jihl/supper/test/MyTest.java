package com.jihl.supper.test;

import com.jihl.supper.SupperApplication;
import com.jihl.supper.bean.Iron;
import com.jihl.supper.bean.Spider;
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
}
