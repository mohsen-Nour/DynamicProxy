package com.mycompany.dynamicproxy.test;

import com.mycompany.dynamicproxy.framework.DynamicProxy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohsen
 */
public class Program {
    
    public static void main(String[] args) {
        
        
        try {
            Service service = DynamicProxy.getInstance(MockService.class, ServiceAspect.class);
            service.sayHello("Java");
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
