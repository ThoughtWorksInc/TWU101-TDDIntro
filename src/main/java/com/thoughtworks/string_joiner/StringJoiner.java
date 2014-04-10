package com.thoughtworks.string_joiner;

import java.util.List;

public class StringJoiner {
    public String join(List<String> strings) {
        String result = "";
        for (String string : strings) {
            result += string;
        }
        return result;
    }
}
