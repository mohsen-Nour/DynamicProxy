
package com.mycompany.dynamicproxy.car;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author mohsen
 */
public class VehicleHandler implements InvocationHandler{
    
    Object obj ;
    
    public VehicleHandler(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
     Method methods[] =   o.getClass().getMethods();
     for(Method m: methods){
         System.out.println(m.getName());
     }
        System.out.println("Vehicle Handler: Invoking "+method.getName());
        return method.invoke(obj, args);
    }

    public static void main(String[] args) {
        IVehicle c = new Car("Jack J5");
        ClassLoader c1 = IVehicle.class.getClassLoader();
        IVehicle v = (IVehicle)Proxy.newProxyInstance(c1, new Class[]{IVehicle.class }, new VehicleHandler(c));
        v.start();
        v.forward();
        v.stop();
    }
}
