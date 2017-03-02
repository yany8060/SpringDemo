package com.yany.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yanyong on 2017/3/2.
 */
public class TraceHandler implements InvocationHandler {
    private Object target;

    public TraceHandler(Object t) {
        target = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法执行前
        System.out.println("before method invoking");

        // invoke actual method
        return method.invoke(target, args);
    }
}
