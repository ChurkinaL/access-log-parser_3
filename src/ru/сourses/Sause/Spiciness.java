package ru.сourses.Sause;

public enum Spiciness {
    VERY_SPICY("Очень острый"), SPICY("Острый"), NOT_SPICY("Не острый");
     private final String spiciness;

    Spiciness(String spiciness) {
        this.spiciness=spiciness;
    }
    public String getSpiciness(){
        return spiciness;
    }
}
