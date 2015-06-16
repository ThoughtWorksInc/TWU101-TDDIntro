package com.thoughtworks.tddintro.string_joiner;

import java.util.List;

public class StringJoiner {
    private String delimiter;

    public StringJoiner(String delimiter) {
        this.delimiter = delimiter;
    }

    public String join(List<String> strings) {
        if (!strings.isEmpty()){
            String firstString = strings.get(0);
            List<String> remainingStrings = strings.subList(1, strings.size());
            return firstString + concatenateWithDelimiter(remainingStrings);
        }
        return "";
    }

    private String concatenateWithDelimiter(List<String> strings) {
        String result = "";
        for (String string : strings) {
            result += delimiter + string;
        }
        return result;
    }
}
