package com.thoughtworks.tddintro.exercises.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Library {
    private List<String> books;
    private PrintStream printStream;
    private BufferedReader bufferedReader;

    public Library(List<String> books, PrintStream printStream, BufferedReader bufferedReader) {
        this.books = books;
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
    }

    public void listBooks() {
        String bookList = "";
        for (String book : books) {
            bookList += book + "\n";
        }
        printStream.println(bookList);
    }

    public void enterBook() {
        printStream.println("Enter a book to add to the collection");
        String book = readLine();
        books.add(book);
    }

    private String readLine() {
        String book = null;
        try {
            book = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }

    public void removeBook() {
        printStream.println("Enter a book to remove from the collection");
        String book = readLine();
        if (books.contains(book)){
            books.remove(book);
        }
    }
}
