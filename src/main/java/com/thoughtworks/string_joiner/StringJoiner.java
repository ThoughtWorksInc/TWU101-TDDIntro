package com.thoughtworks.string_joiner;

import java.util.List;

public class StringJoiner {
    public String join(List<String> strings) {
        if (strings.size() > 0){
            return strings.get(0);
        }
        return "";
    }
}
