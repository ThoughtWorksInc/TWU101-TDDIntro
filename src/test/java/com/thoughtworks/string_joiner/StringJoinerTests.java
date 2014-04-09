package com.thoughtworks.string_joiner;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StringJoinerTests {
    @Test
    public void shouldFooWhenBar(){
        List<String> strings = new ArrayList<String>();
        String result = new StringJoiner().join(strings);
    }
}
