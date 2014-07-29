package com.thoughtworks.demeter;

public class Engine {

  private Throttle throttle;

  public Engine(Throttle throttle) {
    this.throttle = throttle;
  }

  Throttle getThrottle(){
    return throttle;
  }
}
