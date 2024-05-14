package ru.сourses.rest;

import java.util.Arrays;

public class Student {
    String name;

    private int[] grades;

    @Override
    public String toString() {
        return name + ": " + Arrays.toString(grades);//должно быть так   “Имя: [оценка1, оценка2,…,оценкаN]” (toString) .как здесь указать массив оценок???
    }

    public Student(String name, int[] grades) {
        this.grades = grades;
        this.name = name;
    }

    public void addGrade(int grade) {
        if (grade < 2 || grade > 5) {
            System.out.println("Неправильная оценка");
        } else {
            for (int i = 0; i < grades.length; i++) {
                if (grades[i] == 0) {
                    grades[i] = grade;
                    System.out.println(this.toString());
                    return;
                }

            }
            System.out.println("Нет свободных оценок");
        }
    }

    public int[] getGrades(){
        return grades;
    }
    public static void main(String[] args) {
        Student stud = new Student("Любовь", new int[]{5, 4, 5, 0, 0});
        Student stud1 = new Student("Люба", new int[20]);
        System.out.println(stud.toString());
        System.out.println(stud1.toString());
        stud.addGrade(4);
        stud.addGrade(3);
        //stud.addGrade(10);
       // stud.addGrade(2);

    }
}
