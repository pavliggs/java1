package ru.progwards.pavliggs;

public class Point3D extends Point2D {
    private int z;

    Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public String toString() {
        return super.toString() + "," + z;
    }

    public static void main(String[] args) {
        Point2D point2d = new Point2D(4, 7);
        Point2D point3d = new Point3D(4, 6, 5);
        System.out.println(point3d.toString());
    }
}
