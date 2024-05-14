package ru.сourses.rest;

public class CurrencyConverter {
   double eur;
    double usd;
    int rub;

    public void  convertRubToUsd1 (String rubToUsd){
        System.out.println( rub + " рублей сконвертированы в " + (rub/93.32) + " долларов");
    }
    public void  convertRubToUsd2 (String rubToEur){
        System.out.println(rub + " рублей сконвертированы в "+ (rub/100) + " евро");
    }
    public void  convertRubToUsd3 (String usdToRub){
        System.out.println(usd + " доллоров сконвертированны в " + (usd * 93.32)+ " рублей");
    }
    public void  convertRubToUsd4 (String eurToRub){
        System.out.println(eur+ " евро сконвертированны в "+ (eur*100)+ " рублей");
    }

    public static void main(String[] args) {
        CurrencyConverter convert = new CurrencyConverter();
        convert.eur=150;
        convert.rub=80000;
        convert.usd=600;
        convert.convertRubToUsd1(null);
        convert.convertRubToUsd2(null);
        convert.convertRubToUsd3(null);
        convert.convertRubToUsd4(null);
    }
}
