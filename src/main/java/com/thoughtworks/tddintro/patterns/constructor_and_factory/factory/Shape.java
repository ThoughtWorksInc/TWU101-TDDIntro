package com.thoughtworks.tddintro.patterns.constructor_and_factory.factory;

public class Shape {
    private String name;
    private final double area;
    private final double perimeter;

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
}
