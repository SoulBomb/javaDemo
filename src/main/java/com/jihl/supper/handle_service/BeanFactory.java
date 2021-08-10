package com.jihl.supper.handle_service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author admin
 * @since 2021/8/10
 */
@Component
public class BeanFactory {

    @Resource
    private CglibMethodInterceptor cglibMethodInterceptor;

    @Bean("work")
    public WorkInterface getWorkBean() {
        return cglibMethodInterceptor.generateClass(WorkInterface.class);
    }

}
