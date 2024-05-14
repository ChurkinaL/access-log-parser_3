package ru.сourses.Points;

public class Point3D extends Point {
    int z;
    public Point3D(int x, int y,int z) {
        super(x, y);
        this.z=z;
    }

    @Override
    public String toString() {
        return "Point3D{" +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public static void main(String[] args) {
        Point3D point3D= new Point3D(3,4,6);
        System.out.println(point3D.toString());
    }
}
