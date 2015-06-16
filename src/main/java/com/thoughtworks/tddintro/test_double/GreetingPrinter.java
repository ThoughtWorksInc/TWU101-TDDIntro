package com.thoughtworks.tddintro.test_double;

import java.io.PrintStream;

public class GreetingPrinter {
    private PrintStream printStream;

    public GreetingPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printGreeting(){
        printStream.println("Greetings!");
    }
}
