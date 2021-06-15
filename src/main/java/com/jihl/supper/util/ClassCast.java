package com.jihl.supper.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author admin
 * @since 2021/5/14
 */
public class ClassCast {

    public static void main(String[] args) {
        String ss = ss(String.class, "123");
        System.out.println(ss);
    }

    public static  <T> T ss(Class<T> returnClass, String t) {
        if (t.getClass().equals(returnClass)) {
            ObjectMapper objectMapper = new ObjectMapper();
            t = t.replace("1", "2");
            return objectMapper.convertValue(t, returnClass);
        }
        return null;
    }

}
