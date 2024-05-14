package ru.сourses.rest;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int N = 10;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }
        for (int i = 0; i < N - 1; i += 2) {
            int temp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, temp);
        }
        System.out.println("Исходный список:");
        System.out.println(list);
    }
}