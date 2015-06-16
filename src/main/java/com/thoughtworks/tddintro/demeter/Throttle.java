package com.thoughtworks.tddintro.demeter;

public class Throttle {
  private Valve valve;

  public Throttle(Valve valve) {
    this.valve = valve;
  }

  public Valve getValve() {
    return valve;
  }
}
