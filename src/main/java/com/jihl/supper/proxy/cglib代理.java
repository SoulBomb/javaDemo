package com.jihl.supper.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/*代理类不需要实现接口*/
public class cglib代理 {

    public static void main(String[] args) {
        早晨 早晨 = new 早晨(new 起床());
        起床 起床 = (起床) Enhancer.create(起床.class, 早晨);
        起床.做啥事();
    }

    static class 起床 {

        public void 做啥事() {
            System.out.println("躺尸玩手机");
        }
    }

    static class 早晨 implements MethodInterceptor {

        Object obj;
        早晨() {

        }
        早晨(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("醒来");
            return method.invoke(obj, objects);
        }
    }
}
