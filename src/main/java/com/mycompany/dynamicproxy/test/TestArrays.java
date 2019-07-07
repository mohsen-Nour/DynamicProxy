/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dynamicproxy.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.LiteralNode;

/**
 *
 * @author mohsen
 */
public class TestArrays {
    
    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
     
     
        Class<String> clazz = String.class;
        String str = makeInstance(clazz);
        System.out.println(str.length());
    }
    
    public static <T> T makeInstance(Class<T> type){
         Object obj =null;
        try {
            obj= type.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(TestArrays.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (T)obj;
    }
    
    
}
