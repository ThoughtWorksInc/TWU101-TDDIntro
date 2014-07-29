package com.thoughtworks.test_double;

import java.io.OutputStream;
import java.io.PrintStream;

public class FakePrintStream extends PrintStream {
    private String printedString;

    public FakePrintStream() {
        super(new FakeOutputStream());
    }

    @Override
    public void println(String string) {
        printedString = string;
    }

    public String printedString() {
        return printedString;
    }
}
