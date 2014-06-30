package com.thoughtworks.test_double;

import java.io.PrintStream;

public class GreetingPrinter {

    public static void main(String[] args) {
        System.out.println("Greetings!");
        // Can be refactored to...
        PrintStream printStream = System.out;
        printStream.println("Greetings!");

        // Which can in turn be refactored to...
        GreetingPrinter greetingPrinter = new GreetingPrinter(System.out);
        greetingPrinter.printGreeting();
    }

    // Dependency Injection Example

    private PrintStream printStream;

    public GreetingPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printGreeting(){
        printStream.println("Greetings!");
    }
}
