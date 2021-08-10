package com.jihl.supper.handle_service;

import org.springframework.stereotype.Service;

/**
 * @author admin
 * @since 2021/8/10
 */
@Service
@ClaAnnotation("doctor")
public class doctor implements WorkInterface {

    @Override
    public void doWork(String occupation) {
        System.out.println("try my best save everyone");
    }

}
