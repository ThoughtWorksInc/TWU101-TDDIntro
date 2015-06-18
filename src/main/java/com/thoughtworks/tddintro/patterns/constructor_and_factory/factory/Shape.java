package com.thoughtworks.tddintro.patterns.constructor_and_factory.factory;

import static java.lang.Math.PI;

public class Shape {
    private String name;
    private final double area;
    private final double perimeter;

    /*
        Examples of what not to do
     */

    // Square Constructor
    public Shape(double length){
        name = "Square";
        area = length * length;
        perimeter = length * 4;
    }

    // Circle Constructor
    public Shape(float radius){
        name = "Circle";
        area = PI * radius * radius;
        perimeter = 2 * PI * radius;
    }

    /*
        This is a good example of an all arguments constructor
     */
    public Shape(String name, double area, double perimeter) {
        this.name = name;
        this.area = area;
        this.perimeter = perimeter;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public static Shape createSquareWithSidesOfLength(int length) {
        String name = "Square";
        double area = length * length;
        double perimeter = length * 4;
        return new Shape(name, area, perimeter);
    }
}
