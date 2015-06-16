package com.thoughtworks.tddintro.patterns.constructor_and_factory.one_constructor;

public class Product {
    private int numberOfItems;
    private double totalPrice;

    public Product() {
        numberOfItems = 0;
        totalPrice = 0.0;
    }

    public Product(int numberOfItems, double totalPrice) {
        this.numberOfItems = numberOfItems;
        this.totalPrice = totalPrice;
    }

    public double pricePerItem(){
        return totalPrice/numberOfItems;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
