package ru.job4j.pooh;

import java.util.List;

public class ValidateInput {
    public boolean isValid(String[] input) {
        if (input[0].equals("GET")) {
            return input.length == 2 && input[1].split("/").length == 3;
        }
        return input.length == 3;
    }
}
