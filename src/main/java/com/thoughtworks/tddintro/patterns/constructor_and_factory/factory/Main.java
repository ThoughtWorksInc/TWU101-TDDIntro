package com.thoughtworks.tddintro.patterns.constructor_and_factory.factory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        // Static Factory Method
        Shape square = Shape.createSquareWithSidesOfLength(5);
        shapes.add(square);

        // Abstract Factory
        Shape circle = new CircleFactory().create(5);
        shapes.add(circle);

        for (Shape shape : shapes) {
            System.out.println(shape.getName() + " has an area of: " + shape.getArea() + " and a perimeter of: " + shape.getPerimeter());
        }
    }
}
