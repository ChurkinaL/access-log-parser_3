package ru.сourses.rest;

public class ObjectCalculator {
    public static void main(String[] args) {
        ObjectCalculator calc = new ObjectCalculator();
        int x = 5;
        int y = 6;
        System.out.println(calc.delenie(x, y));
        System.out.println(calc.sum(x, y));
        System.out.println(calc.proisvedenie(x, y));
        System.out.println(calc.minus(x, y));
    }

    private int sum(int a, int b) {
        return a + b;
    }

    private int minus(int a, int b) {
        return a - b;
    }

    private int proisvedenie(int a, int b) {
        return a * b;
    }

    private double delenie(int a, int b) {
        return (double) a / b;
    }
}
