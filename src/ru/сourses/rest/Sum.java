package ru.сourses.rest;

public class Sum {
    public static void main(String[] args) {
        double s=0;
        for (int i = 0; i < args.length; i++) {
            try {
                s=s+ Double.parseDouble(args[i]);
            } catch (NumberFormatException bag){
                s=s+0;
                System.out.println(bag.toString());
            }


        }
        System.out.println(s);

    }
}
