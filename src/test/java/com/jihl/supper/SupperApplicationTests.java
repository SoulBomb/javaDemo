package com.jihl.supper;

import com.jihl.supper.myspring.DaXiong;
import com.jihl.supper.myspring.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

@SpringBootTest
class SupperApplicationTests {

    @Test
    void contextLoads() {
        BigDecimal paymentAount = new BigDecimal(0);
        boolean a = paymentAount.compareTo(BigDecimal.ZERO) != 0;
        System.out.println(a);
    }

    @Test
    void testBean() {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(TestConfig.class);
        DaXiong bean = context.getBean(DaXiong.class);
        bean.doIt("take me go home!!!!!!");
        context.close();
    }
}
