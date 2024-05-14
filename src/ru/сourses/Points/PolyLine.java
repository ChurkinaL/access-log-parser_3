package ru.сourses.Points;

public class PolyLine implements Measurable {
    Point[] points;
    public PolyLine (Point...points){
        this.points=points;
    }
    public double length(){
        double sum=0;
        for (int i = 0; i < points.length-1; i++) {
            double length = points[i].x - points[i+1].x;
            double length1 = points[i].y - points[i+1].y;
            sum=sum+ Math.sqrt(length1*length1+length*length);//length;
        }
        return sum;
    }

    @Override
    public boolean equals(Object obj) {
        PolyLine polyLine= (PolyLine) obj;
        if (this.points.length != polyLine.points.length){
            return false;
        }
        else {
            boolean equal = true;
            for (int i = 0; i < this.points.length-1; i++) {
                if (!this.points[i].equals(polyLine.points[i])) {
                    return false;
                }
            }
            return equal;
        }
    }

    public static void main(String[] args) {
        PolyLine l1=new PolyLine(new Point(3,5),new Point(4,-6),new Point(7,8));
        PolyLine l2=new PolyLine(new Point(3,5),new Point(4,6),new Point(7,8));
        System.out.println(l1.equals(l2));

    }
}
