package ru.сourses.Sause;

public class Sauce {
    String name;
    Spiciness spiciness;

    public Sauce(String name, Spiciness spiciness) {
        this.name = name;
        this.spiciness = spiciness;
    }

    @Override
    public String toString() {
        return "Соус " +
                 name +
                ": " + spiciness.getSpiciness();
    }

    public static void main(String[] args) {
            Sauce sauce=new Sauce("Махеев",Spiciness.SPICY);
        Sauce sauce1=new Sauce("Слобода",Spiciness.VERY_SPICY);
        Sauce sauce2=new Sauce("Майонез",Spiciness.NOT_SPICY);
        System.out.println(sauce);
        System.out.println(sauce1);
        System.out.println(sauce2);
    }
}
