package ru.сourses.rest;

public class Fraction extends Number {
    final int numerator;
    final int denominator;

    @Override
    public byte byteValue() {
        return super.byteValue();
    }

    @Override
    public short shortValue() {
        return super.shortValue();
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }

    public Fraction(int numerator, int denominator) {
        super();
        if (denominator < 0) {
            System.out.println("Для знаменателя отрицательное значение недопустимо");
            throw new IllegalArgumentException();
        }
        if (denominator == 0) {
            System.out.println("Для знаменателя значение нуль недопустимо");
            throw new IllegalArgumentException();
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction plus(Fraction other) {
        int newNumerator = this.numerator * other.denominator + this.denominator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        return result;
    }

    public Fraction minus(Fraction other) {
        int newNumerator = this.numerator * other.denominator - this.denominator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        Fraction result = new Fraction(newNumerator, newDenominator);
        return result;
    }

    public Fraction plus(int number) {
        int newNumerator = this.numerator * 1 + this.denominator * number;
        int newDenominator = this.denominator * 1;
        Fraction result = new Fraction(newNumerator, newDenominator);
        return result;
    }

    public static Fraction convertToFraction(double doubleToConvert) {
        int wholePart = (int) doubleToConvert;
        String[] partsOfDouble = Double.toString(doubleToConvert).split("\\.");
        int length = partsOfDouble[1].length();
        int fractionPart = Integer.parseInt(partsOfDouble[1]);
        int numerator = (int) (wholePart*(Math.pow(10,length))+fractionPart);
        int denominator = (int) Math.pow(10,length);
        return new Fraction(numerator,denominator);
    }
    public static Fraction sumAll (Object...numbers){
        Fraction resultFraction = new Fraction(0,1);
        for (int i = 0; i < numbers.length; i++) {
            if( numbers[i] instanceof Fraction) {
                resultFraction=resultFraction.plus((Fraction) numbers[i]);

                //вызываем метод который прибавляет к нашей нулевой дроби новую дробь
            } else if (numbers[i] instanceof Integer) {
                resultFraction=resultFraction.plus((Integer) numbers[i]);
                //вызываем метод который прибавляет к нашей нулевой дроби целое число
            }else if (numbers[i] instanceof Double) {
                Fraction convertedFraction = convertToFraction((Double) numbers[i]);
                resultFraction=resultFraction.plus(convertedFraction);
                //вызываем метод который прибавляет к нашей нулевой дроби дробное число ЧЕРЕЗ МЕТОД convertToFraction!!!
            }
        }
        return resultFraction;
    }
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    private Fraction reduce() {
        int gcd = Math.abs(gcd(numerator, denominator));
        return new Fraction(numerator / gcd, denominator / gcd);
    }


    public static void main(String[] args) {
        Fraction f1 = new Fraction(2, 3);
        Fraction f2 = new Fraction(2, 5);
        Fraction f3 = new Fraction(7, 8);
        Fraction f4 = new Fraction(5, 1);
        //System.out.println(f4.toString());
        //System.out.println(f1.plus(f2).minus(f3).toString());
       // System.out.println(f1.plus(f2).plus(f3).minus(f4));
        //System.out.println(f1.plus(5));
        //System.out.println(convertToFraction(2.35));
        System.out.println(sumAll(3.6,new Fraction(49,12),3,new Fraction(3,2)).reduce());
        System.out.println(sumAll(2,new Fraction(3,5),2.3).reduce());
        System.out.println(sumAll(new Fraction(1,3),1).reduce());
    f1.equals(f2);
    }


    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
