package com.thoughtworks.tddintro.library;

import org.joda.time.format.DateTimeFormat;

import java.util.ArrayList;
import java.util.List;

import static org.joda.time.DateTime.now;

public class Main {
    public static void main(String[] args) {
        Library library = new Library(books(), System.out, DateTimeFormat.shortTime());
        library.welcome(now());
        library.listBooks();
    }

    private static List<String> books() {
        List<String> books = new ArrayList<>();
        books.add("Head First Java");
        books.add("Test Driven Development by Example");
        books.add("The Agile Samurai");
        return books;
    }
}
