package com.jihl.supper.schedul;

import org.springframework.stereotype.Component;

/**
 * @author admin
 * @since 2021/7/26
 */
@Component
public class DemoTask {

    public void taskWithParams(String param1, Integer param2) {
        System.out.println("这是有参示例任务：" + param1 + param2);
    }

    public void taskNoParams() {
        System.out.println("这是无参示例任务");
    }

}
