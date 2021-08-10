package com.jihl.supper.controller;

import com.jihl.supper.handle_service.WorkInterface;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author admin
 * @since 2021/8/10
 */
@RestController
public class MyController {

    @Resource(name = "work")
    private WorkInterface workInterface;

    @GetMapping("work")
    public void work(String occupation) {
        workInterface.doWork(occupation);
    }

}
