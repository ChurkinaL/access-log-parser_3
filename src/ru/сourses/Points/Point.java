package ru.сourses.Points;

public class Point {
    int x;
    int y;
    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }

    @Override
    public boolean equals(Object obj) {
        Point point= (Point) obj;
        if (this.x== point.x && this.y== point.y){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "ru.сourses.Points.Point {" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Point point= this;
        return new Point(point.x, point.y);
    }

    public static void main(String[] args) {
        Point point = new Point(3,5);
        System.out.println(point.toString());
        System.out.println(point.equals(new Point(4,5)));
        try {
            System.out.println(point.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
