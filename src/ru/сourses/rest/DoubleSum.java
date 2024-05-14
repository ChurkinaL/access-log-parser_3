package ru.сourses.rest;

import java.util.ArrayList;

public class DoubleSum {
    public static double sumAll(double... numbers){
        double sum=0.0;
        for(double num: numbers){
            sum+= num;
        }
        return sum;
    }

    public static void main(){
        ArrayList<Double> sums=new ArrayList<>();
        double sum1 = sumAll(2,(double) 3/5,2.3);
        double sum2 = sumAll(3.6,(double)49/12,3,(double)3/2);
        double sum3 = sumAll((double)1/3+1);
        sums.add(sum1);
        sums.add(sum2);
        sums.add(sum3);
        for(double sum: sums){
            System.out.println(sum);
        }
    }
}
