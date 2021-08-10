package com.jihl.supper.handle_service;

import org.springframework.stereotype.Service;

/**
 * @author admin
 * @since 2021/8/10
 */
@Service
@ClaAnnotation("programmer")
public class programmer implements WorkInterface {

    @Override
    public void doWork(String occupation) {
        System.out.println("I love BUG");
    }

}
