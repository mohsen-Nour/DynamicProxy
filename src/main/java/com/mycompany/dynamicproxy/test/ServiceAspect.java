package com.mycompany.dynamicproxy.test;

/**
 *
 * @author mohsen
 */
public final class ServiceAspect {
    
    public static  void beforeSayHello(Service service,String name){
        System.out.println("before sayHello(" +name+" )");
    }
    
    public static  void afterSayHello(Service service,String name,String result){
        System.out.println("after sayHello(" +name+" ) : "+result);
    }
    
    public static void throwSayHello(Service service, String name, Throwable t){
        System.out.println("throw sayHello (" + name +")");
    }
    
    public static  String insteadSayHello(Service service,String name){
        System.out.println("Instead sayHello(" +name+" )");
        return "Just saying nothing.";
    }
}
