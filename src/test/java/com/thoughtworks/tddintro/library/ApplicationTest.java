package com.thoughtworks.tddintro.library;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ApplicationTest {

    @Test
    public void shouldListBooksWhenStarting() {
        Library library = mock(Library.class);
        Application application = new Application(library);

        application.start();


        verify(library).listBooks();

    }

}