package com.thoughtworks.string_joiner;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringJoinerTests {
    private List<String> strings;
    private StringJoiner joiner;

    @Before
    public void setUp() throws Exception {
        strings = new ArrayList<String>();
        joiner = new StringJoiner();
    }

    @Test
    public void shouldJoinIntoAnEmptyStringWhenListIsEmpty(){
        assertThat(joiner.join(strings), is(""));
    }

    @Test
    public void shouldJoinIntoTheStringWhenListIsOneString(){
        String aString = "A String";
        strings.add(aString);
        assertThat(joiner.join(strings), is(aString));
    }

    @Test
    public void shouldContainBothStringsWhenListIsTwoStrings(){
        strings.add("A");
        strings.add("B");
        assertThat(joiner.join(strings), both(containsString("A")).and(containsString("B")));
    }
}
