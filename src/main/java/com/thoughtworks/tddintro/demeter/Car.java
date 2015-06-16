package com.thoughtworks.tddintro.demeter;

public class Car {

  private Engine engine;

  public Car(Engine engine) {
    this.engine = engine;
  }

  public void accelerate() {
    // Law of Demeter Violation
    engine.getThrottle().getValve().open(100);
  }
}
