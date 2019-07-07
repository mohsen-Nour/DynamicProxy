
package com.mycompany.dynamicproxy.shape;

/**
 *
 * @author mohsen
 */
public class Rectangle  implements IShape{

    @Override
    public void draw() {
        System.out.println("Rectancle Draw");
    }

    @Override
    public void print() {
        System.out.println("Rectancle Print");
    }

    @Override
    public void move() {
        System.out.println("Rectancle move");
    }

    @Override
    public void resize() {
        System.out.println("Rectancle resize");
    }

}
