package nadiatests;

import java.awt.*;
// Java provides java.awt.Point already
public class Circle {

    private int radius;
    private Point point;

    public Circle() {
        point = new Point(0, 0);
        radius = 1;
    }

    public Circle(int x, int y, int r) {
        point = new Point(x, y);
        radius = r;
    }

    public int getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    public static void main(String args[]) {
        Circle circle = new Circle(1,2,3);
        System.out.println("Area: "+circle.getArea());
        System.out.println("Permeter: "+circle.getPerimeter());

    }
}

