package com.thoughtworks.tddintro.test_double;

import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Greetings!");

        // Can be refactored to...
        PrintStream printStream = System.out;
        printStream.println("Greetings!");

        // Which can in turn be refactored to...
        GreetingPrinter greetingPrinter = new GreetingPrinter(System.out);
        greetingPrinter.printGreeting();
    }
}
