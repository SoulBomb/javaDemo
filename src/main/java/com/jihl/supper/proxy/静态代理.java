package com.jihl.supper.proxy;

import org.junit.Test;

public class 静态代理 {

    @Test
    public void test() {
        生活 生活 = new 早晨();
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

    class 早晨 implements 生活 {

        起床 起床 = new 起床();

        @Override
        public void 做啥事() {
            System.out.println("醒来");
            起床.做啥事();
        }
    }

}
