package com.yany.proxy;

/**
 * Created by yanyong on 2017/3/2.
 */
public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("call doSomething()");
    }
}
