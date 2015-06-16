package com.thoughtworks.tddintro.factorial;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/*

    * Change Factorial.compute() so that the first test passes and the remaining tests fail.
    * Change Factorial.compute() so that the first two tests pass and the remaining tests fail.
    ...
    * Change Factorial.compute() so that all of the tests pass.

 */

public class FactorialTests {

    private final Factorial factorial = new Factorial();

    @Test // Make me pass first
    @Ignore // Remove each @Ignore to allow the test to run
    public void shouldReturnOneWhenNumberIsOne(){
        assertThat(new Factorial().compute(1), is(1));
    }

    @Test
    @Ignore // Remove each @Ignore to allow the test to run
    public void shouldReturnTwoWhenNumberIsTwo(){
        assertThat(factorial.compute(2), is(2));
    }

    @Test
    @Ignore // Remove each @Ignore to allow the test to run
    public void shouldReturnOneWhenNumberIsZero(){
        assertThat(factorial.compute(0), is(1));
    }

    @Test
    @Ignore // Remove each @Ignore to allow the test to run
    public void shouldReturnSixWhenNumberIsThree(){
        assertThat(factorial.compute(3), is(6));
    }

    @Test(expected = IllegalArgumentException.class)
    @Ignore // Remove each @Ignore to allow the test to run
    public void shouldThrowIllegalArgumentExceptionWhenNumberIsNegative(){
        factorial.compute(-1);
    }
}