package com.thoughtworks.tddintro.patterns.constructor_and_factory.factory;

import static java.lang.Math.PI;

public class CircleFactory implements ShapeFactory {
    @Override
    public Shape create(int diameter) {
        double radius = 1.0 * diameter/2.0;
        return new Shape("Circle", PI * radius * radius, 2 * PI * radius);
    }
}
