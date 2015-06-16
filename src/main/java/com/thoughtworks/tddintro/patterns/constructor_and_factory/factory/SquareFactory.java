package com.thoughtworks.tddintro.patterns.constructor_and_factory.factory;

public class SquareFactory {
    public Shape create(int width) {
        double area = width * width;
        double perimeter = width * 4;
        String name = "Square";
        return new Shape(name, area, perimeter);
    }
}
