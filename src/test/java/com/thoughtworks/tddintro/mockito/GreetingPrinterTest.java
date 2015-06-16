package com.thoughtworks.tddintro.mockito;

import com.thoughtworks.tddintro.test_double.GreetingPrinter;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GreetingPrinterTest {

    @Test
    public void shouldPrintGreeting() {
        PrintStream printStream = mock(PrintStream.class);
        GreetingPrinter greetingPrinter = new GreetingPrinter(printStream);

        greetingPrinter.printGreeting();

        verify(printStream).println("Greetings!");
    }
}