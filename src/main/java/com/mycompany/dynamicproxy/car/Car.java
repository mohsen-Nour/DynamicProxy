package com.mycompany.dynamicproxy.car;

public class Car implements IVehicle {
    private String name;
    public Car(String name){
        this.name = name;
    }

    @Override
    public void start(){
        System.out.println(" Car " + name + " Started");
  }
    @Override
  public void stop(){
      System.out.println(" Car " + name + " stoped");
  }

    @Override
  public void forward(){
      System.out.println(" Car " + name + " forwared");
  }

    @Override
  public void reverse(){
      System.out.println(" Car " + name + " reversed");
  }

    @Override
  public String getName(){
      return name;
  }
  
  public void setName(String name){
      this.name = name;
  }
}