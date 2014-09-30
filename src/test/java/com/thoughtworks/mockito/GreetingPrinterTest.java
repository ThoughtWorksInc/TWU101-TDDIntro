package com.thoughtworks.mockito;

import com.thoughtworks.test_double.FakePrintStream;
import com.thoughtworks.test_double.GreetingPrinter;
import org.junit.Test;

import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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