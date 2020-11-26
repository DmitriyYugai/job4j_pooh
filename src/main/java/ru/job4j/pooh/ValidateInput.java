package ru.job4j.pooh;

import java.util.List;

public class ValidateInput {
    public boolean isValid(List<String> input) {
        if (input.get(0).split(" ").length != 2) {
            return false;
        }
        if (input.get(0).contains("GET")) {
            return input.size() == 1;
        }
        return input.size() == 2;
    }
}
