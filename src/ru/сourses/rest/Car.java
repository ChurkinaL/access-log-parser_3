package ru.сourses.rest;

public class Car {
    String color;
    int maxSpeed;
    String model;
    public Car(String color, int maxSpeed, String model){
        this.model=model;
        this.color=color;
        this.maxSpeed=maxSpeed;
    }

    @Override
    public String toString() {
        return "Машина "+model+" "+color+" цвета, едет со скоростью "+ maxSpeed+ " км/ч";
    }

    public static void main(String[] args) {
        Car lada = new Car("белого",30,"Лада");
        System.out.println(lada.toString());
        Car lamborghini = new Car("чёрного",230,"Ламборгини");
        System.out.println(lamborghini.toString());
    }
    //public void drive (String mark) {
        //System.out.println("Машина "+mark+" едет со скоростью "+ maxSpeed);
    //}

}
