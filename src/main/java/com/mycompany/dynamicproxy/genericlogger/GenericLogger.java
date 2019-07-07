
package com.mycompany.dynamicproxy.genericlogger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * @author mohsen
 */
public class GenericLogger  implements InvocationHandler{
    Object target;

    public GenericLogger(Object target) {
        this.target =target;
    }
    

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.println("Generic Logger Entry:Invoking "+method.getName());
        return method.invoke(target, args);
    }

}
