package ru.сourses.Points;

public class ClosedPolyLine extends PolyLine {
    @Override
    public double length() {
        double sum = 0;
        for (int i = 0; i < points.length - 1; i++) {
            double length = points[i].x - points[i + 1].x;
            double length1 = points[i].y - points[i + 1].y;
            sum = sum + Math.sqrt(length1 * length1 + length * length);//length;
        }
        sum = sum + Math.sqrt((points[0].x - points[points.length - 1].x) * (points[0].x - points[points.length - 1].x) + (points[0].y - points[points.length - 1].y) * (points[0].y - points[points.length - 1].y));//считаем расстояние между первой и последней точкой массива
        return sum;
    }

    public ClosedPolyLine(Point... points) {
        super(points);
    }

    public static void main(String[] args) {
        Line line = new Line(new Point(2, 4), new Point(4, 2));

        PolyLine polyLine = new PolyLine(new Point(2, 4), new Point(4, 2), new Point(7, 4));
        ClosedPolyLine closedPolyLine = new ClosedPolyLine(new Point(2, 4), new Point(4, 2), new Point(7, 4));
        Measurable[] measurable = new Measurable[]{line, polyLine, closedPolyLine};
        for (int i = 0; i < measurable.length; i++) {
            System.out.println(measurable[i].getClass().getSimpleName() + ": " + measurable[i].length());

        }
    }
}
