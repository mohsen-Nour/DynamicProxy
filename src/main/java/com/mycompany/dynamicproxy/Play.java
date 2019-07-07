package com.mycompany.dynamicproxy;

import com.mycompany.dynamicproxy.car.Car;
import com.mycompany.dynamicproxy.car.IVehicle;
import com.mycompany.dynamicproxy.genericlogger.GenericLogger;
import com.mycompany.dynamicproxy.shape.IShape;
import com.mycompany.dynamicproxy.shape.Rectangle;
import java.lang.reflect.Proxy;

/**
 *
 * @author mohsen
 */
public class Play {

    public static void main(String[] args) {
        carLogging();
        recatangleLogging();
    }

    public static void carLogging() {
        IVehicle c = new Car("Botar");
        ClassLoader cl = IVehicle.class.getClassLoader();
        IVehicle v = (IVehicle) Proxy.newProxyInstance(cl,
                new Class[]{IVehicle.class}, new GenericLogger(c));
        v.start();
        v.forward();
    }

    public static void recatangleLogging() {
        IShape rect = new Rectangle();
        ClassLoader cl = IShape.class.getClassLoader();
        IShape s = (IShape) Proxy.newProxyInstance(cl,
                new Class[]{IShape.class}, new GenericLogger(rect));
        s.draw();
        s.move();
        s.resize();
    }
}
