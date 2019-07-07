package com.mycompany.dynamicproxy.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import jdk.nashorn.internal.ir.LiteralNode;

/**
 *
 * @author mohsen
 */
public class DynamicProxy implements InvocationHandler {

    private Object instance;
    private Hashtable<Method, Method> beforeMethods;
    private Hashtable<Method, Method> afterMethods;
    private Hashtable<Method, Method> instedMethods;
    private Hashtable<Method, Method> throwMethods;

    private DynamicProxy(Class<?> type, Class<?> aspect) throws InstantiationException, IllegalAccessException {
        this.instance = type.newInstance();
        this.beforeMethods = new Hashtable<>();
        this.afterMethods = new Hashtable<>();
        this.instedMethods = new Hashtable<>();
        this.throwMethods = new Hashtable<>();
        for (Class<?> interfaceType : type.getInterfaces()) {
            for (Method interfaceMethod : interfaceType.getDeclaredMethods()) {
                String name = interfaceMethod.getName();
                name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                for (Method aspectMethod : aspect.getDeclaredMethods()) {
                    if (!Modifier.isStatic(aspectMethod.getModifiers())) {
                        continue;
                    }
                    if (aspectMethod.getName().equals("before" + name)) {
                        this.beforeMethods.put(interfaceMethod, aspectMethod);
                    } else if (aspectMethod.getName().equals("after" + name)) {
                        this.afterMethods.put(interfaceMethod, aspectMethod);
                    } else if (aspectMethod.getName().equals("instead" + name)) {
                        this.instedMethods.put(interfaceMethod, aspectMethod);
                    } else if (aspectMethod.getName().equals("throw" + name)) {
                        this.instedMethods.put(interfaceMethod, aspectMethod);
                    }
                }
            }
        }

    }

   

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        if (beforeMethods.containsKey(method)) {
            List<Object> parameters = new Vector<>();
            parameters.add(this.instance);
            parameters.addAll(Arrays.asList(args));
            Method beforeMethod = this.beforeMethods.get(method);
            beforeMethod.invoke(null, parameters.toArray(new Object[parameters.size()]));

        }
        Object result = null;
        if (this.instedMethods.containsKey(method)) {
            List<Object> parameters = new ArrayList<>();
            parameters.add(this.instance);
            parameters.addAll(Arrays.asList(args));
            Method insteadMethod = this.instedMethods.get(method);
            result = insteadMethod.invoke(null, parameters.toArray(new Object[parameters.size()]));
        } else {
            if (this.throwMethods.containsKey(method)) {
                try {
                    result = method.invoke(this.instance, args);
                } catch (Throwable t) {
                    List<Object> parameters = new ArrayList<>();
                    parameters.add(this.instance);
                    parameters.addAll(Arrays.asList(args));
                    parameters.add(t);
                    Method throwMethod = this.throwMethods.get(method);
                    throwMethod.invoke(null, parameters.toArray(new Object[parameters.size()]));
                }
            }
            result = method.invoke(this.instance, args);
        }

        if (afterMethods.containsKey(method)) {
            List<Object> parameters = new ArrayList<>();
            parameters.add(this.instance);
            parameters.addAll(Arrays.asList(args));
            parameters.add(result);
            Method afterMethod = this.afterMethods.get(method);
            afterMethod.invoke(null, parameters.toArray(new Object[parameters.size()]));

        }

        return result;
    }
    
     public static <T> T getInstance(Class<T> type, Class<?> aspect) throws InstantiationException, IllegalAccessException {
        DynamicProxy dynamicProxy = new DynamicProxy(type, aspect);
        return (T) Proxy.newProxyInstance(type.getClassLoader(), type.getInterfaces(), dynamicProxy);
    }

    public static Object makeInstance(String fullClassName) {
        String[] splited = fullClassName.split("\\s+");
        System.out.println(splited[1]);
        try {
            Class<?> c = Class.forName(splited[1]);
            Constructor<?> cons = c.getConstructor();
            Object obj = cons.newInstance();
            return obj;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
