package com.thoughtworks.test_double;

import org.junit.Test;

import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GreetingPrinterTest {

    @Test
    public void shouldPrintGreeting() {
        FakePrintStream printStream = new FakePrintStream();
        GreetingPrinter greetingPrinter = new GreetingPrinter(printStream);

        greetingPrinter.printGreeting();

        assertThat(printStream.printedString(), is("Greetings!"));
    }

}