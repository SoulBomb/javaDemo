package com.jihl.supper.myspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @since 2021/9/1
 */
@Configuration
public class TestConfig {

    @Bean
    public Doraemon setDoraemon() {
        return new Doraemon();
    }

    @Bean
    public DaXiong setDaXiong(Doraemon doraemon) {
        DaXiong daXiong = new DaXiong();
        daXiong.callDoraemon(doraemon);
        return daXiong;
    }
}
