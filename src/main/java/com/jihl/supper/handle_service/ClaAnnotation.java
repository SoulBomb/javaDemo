package com.jihl.supper.handle_service;

import java.lang.annotation.*;

/**
 * @author admin
 * @since 2021/8/10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ClaAnnotation {

    String value();

}
