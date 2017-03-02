package com.yany.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by yanyong on 2017/3/2.
 */
public class ProxyTest {

    @Test
    public void proxyTest() {
        RealSubject real = new RealSubject();

        InvocationHandler handler = new TraceHandler(real);

        Subject proxy = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(), new Class[]{Subject.class}, handler);

        proxy.doSomething();

    }
}
