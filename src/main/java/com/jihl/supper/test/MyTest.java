package com.jihl.supper.test;

import com.jihl.supper.SupperApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author admin
 * @since 2021/4/29
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SupperApplication.class)
public class MyTest {

    @Test
    public void cC() {
        System.out.println(1);
    }
}
