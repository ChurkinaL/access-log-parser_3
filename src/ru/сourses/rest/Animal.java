package ru.сourses.rest;

import java.util.Arrays;

public class Animal {
    String dateOfBirth;
    String family;
    String genus;
    double weight;

   //ublic void printAnimal1Data (){
    //  System.out.println(genus+" "+dateOfBirth +" "+"в зоопарке родился"+" "+genus+ " " + "с весом"+ " "+ weight);
   //
    public Animal(String genus,String dateOfBirth,double weight){
  this.weight=weight; this.genus=genus;this.dateOfBirth=dateOfBirth;}
    @Override
    public String toString() {
        return genus+" "+dateOfBirth +" "+"в зоопарке родился"+" "+ " " + "с весом"+ " "+ weight;
    }

    public static void main(String[] args) {
        Animal horse =new Animal("Лошадь","21.04.2024",300.5);
        Animal cat = new Animal("Кот","34.13.2098",134);
        //System.out.println(horse.toString());
        //System.out.println(cat.toString());
       //rse.printAnimal1Data();
      //cat.printAnimal1Data();
        Student stud = new Student("Любовь", new int[]{5, 4, 5, 0, 0});

        System.out.println(Arrays.toString(stud.getGrades()));
    }

}
