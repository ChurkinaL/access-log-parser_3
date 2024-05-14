package ru.сourses.Points;

public class Line implements Measurable{
    Point startPoint;
    Point endPoint;

    @Override
    public boolean equals(Object obj) {
        Line line= (Line) obj;
        if (this.startPoint.equals(line.startPoint) && this.endPoint.equals(line.endPoint) ){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public double length() {
        double sum=0;
        double length = startPoint.x - endPoint.x;
        double length1 = startPoint.y - endPoint.y;
        sum= Math.sqrt(length1*length1+length*length);
        return sum;
    }

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public static void main(String[] args) {
        Line line1= new Line(new Point(3,5),new Point(6,7));
        Line line2= new Line(new Point(3,-5),new Point(6,7));
        System.out.println(line1.equals(line2));

    }
}
