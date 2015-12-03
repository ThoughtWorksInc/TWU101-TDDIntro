package com.thoughtworks.tddintro.library;

public class Application {

    private Library library;

    public Application(Library library) {
        this.library = library;
    }

    public void start(){
        library.listBooks();
    }
}
