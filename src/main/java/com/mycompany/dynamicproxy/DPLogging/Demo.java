package com.mycompany.dynamicproxy.DPLogging;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mohsen
 */
interface Human {
    void talk();
    void walk();
}

class Person implements Human {

    @Override
    public void talk() {
        System.out.println("I am talking");
    }

    @Override
    public void walk() {
        System.out.println("I am walking");
    }

}

class LoggingHandler implements InvocationHandler {

    private final Object target;
    private final Map<String, Integer> calls = new HashMap<>();
    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        calls.merge(name, 1, Integer::sum);
        if (name.contains("toString")) {
            return calls.toString();
        }
        System.out.println("traget class name:  "+target.getClass().getSimpleName());
        return method.invoke(target, args);
    }
}

public class Demo {

    public static <T> T withLogging(T target, Class<T> itfs) {
        return (T) Proxy.newProxyInstance(
                itfs.getClassLoader(),
                new Class<?>[]{itfs},
                new LoggingHandler(target));
    }

    public static void main(String[] args) {
         Person person = new Person();
         Human loggedHuman = withLogging(person, Human.class);
         loggedHuman.walk();
         loggedHuman.talk();
         loggedHuman.talk();
         loggedHuman.talk();
         System.out.println( loggedHuman.toString());
    }
}
