package com.thoughtworks.tddintro.demeter;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarTest {

  @Test
  public void shouldOpenValveWhenAccelerating() {
    Valve valve = mock(Valve.class);

    Throttle throttle = mock(Throttle.class);
    when(throttle.getValve()).thenReturn(valve);

    Engine engine = mock(Engine.class);
    when(engine.getThrottle()).thenReturn(throttle);

    Car car = new Car(engine);

    car.accelerate();

    verify(valve).open(100);
  }
}