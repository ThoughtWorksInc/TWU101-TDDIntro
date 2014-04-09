package com.thoughtworks.string_joiner;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringJoinerTests {
    @Test
    public void shouldJoinIntoAnEmptyStringWhenListIsEmpty(){
        List<String> strings = new ArrayList<String>();
        String result = new StringJoiner().join(strings);
        assertThat(result, is(""));
    }
}
