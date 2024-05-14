package ru.сourses.rest;

public class Calculator {
    public static void main(String[] args) {
        int x = 8;
        int y = 9;
        System.out.println(sum(x,y));
        System.out.println(minus(x,y));
        System.out.println(proisvedenie(x,y));
        System.out.println(delenie(x,y));


    }

    private static int sum (int a, int b) {
        return a+b;
    }

    private static int minus (int a, int b) {
        return a-b;
    }

    private static int proisvedenie (int a, int b) {
        return a*b;
    }

    private static double delenie (int a, int b) {
        return (double) a/b;
    }
}
