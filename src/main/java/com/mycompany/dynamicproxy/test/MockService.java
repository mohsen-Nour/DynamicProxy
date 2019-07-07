package com.mycompany.dynamicproxy.test;

import java.text.MessageFormat;

/**
 *
 * @author mohsen
 */
public class MockService implements Service{

    @Override
    public String sayHello(String name) {
        return MessageFormat.format("Hello, {0}! ", name);
    }
    
    public String toString(){
        return "MockService";
    }
}
