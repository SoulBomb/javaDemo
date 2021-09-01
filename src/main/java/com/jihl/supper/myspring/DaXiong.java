package com.jihl.supper.myspring;

/**
 * @author admin
 * @since 2021/9/1
 */
public class DaXiong {

    private Doraemon doraemon;

    public void callDoraemon(Doraemon doraemon) {
        this.doraemon = doraemon;
    }

    public void doIt(String someThing) {
        doraemon.help(someThing);
    }
}
