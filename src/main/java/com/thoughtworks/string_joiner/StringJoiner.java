package com.thoughtworks.string_joiner;

import java.util.ArrayList;
import java.util.List;

public class StringJoiner {
    private String delimiter;

    public StringJoiner(String delimiter) {
        this.delimiter = delimiter;
    }
    public String join(List<String> strings) {
        String result = "";
        if (strings.size() > 0){
            List<String> allExceptFirstString = new ArrayList<String>(strings);
            result += allExceptFirstString.remove(0);
            for (String string : allExceptFirstString) {
                result += delimiter + string;
            }
        }
        return result;
    }
}
