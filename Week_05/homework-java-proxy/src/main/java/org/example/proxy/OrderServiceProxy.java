package org.example.proxy;

import lombok.extern.slf4j.Slf4j;
import org.example.service.OrderService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class OrderServiceProxy implements InvocationHandler {
    private Object target;

    public void OrderServiceProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("Start the proxy");
        long startTime = System.currentTimeMillis();
        Object invoke = method.invoke(target, args);
        log.info("执行目标方法花费时间：" + (System.currentTimeMillis() - startTime));
        log.info("Complete the proxy");
        return invoke;
    }

    public OrderService getProxyObject() {
        return (OrderService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), new OrderServiceProxy(target));
    }
}
