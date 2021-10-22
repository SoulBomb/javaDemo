package com.jihl.supper.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
* 被代理的类需要实现接口
* */
public class 动态代理 {

    @Test
    public void test() {
        早晨 早晨 = new 早晨(new 起床());
        生活 生活 = (生活)Proxy.newProxyInstance(动态代理.class.getClassLoader(), new Class[]{生活.class}, 早晨);
        生活.做啥事();
    }

    interface 生活 {
        void 做啥事();
    }

    class 起床 implements 生活 {

        @Override
        public void 做啥事() {
            System.out.println("躺尸玩手机");
        }

    }

    class 早晨 implements InvocationHandler {

        Object obj;

        早晨(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("醒来");
            return method.invoke(obj, args);
        }
    }
}
