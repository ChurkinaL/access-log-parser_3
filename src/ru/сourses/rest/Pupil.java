package ru.сourses.rest;

public class Pupil {
    int clas;
    int god;
    String teacher;
    String name;

    public Pupil(String name, int god){
        this.name=name;
        this.god=god;
    }
    //public void printUchenik() {
        //System.out.println("Ученик " + name + " ходит в школу с " + god + " года");
    //}
    @Override
   public String toString() {
        return "Ученик " + name + " ходит в школу с " + god + " года";
    }

    public static void main(String[] args) {
        Pupil vasya = new Pupil("Вася", 1997);
        Pupil luba = new Pupil("Люба",1998);
        //vasya.clas = 5;
       // vasya.teacher = "Любовь Игоревна";
        //vasya.printUchenik();
        System.out.println(vasya.toString());
        System.out.println(luba.toString());

}
}