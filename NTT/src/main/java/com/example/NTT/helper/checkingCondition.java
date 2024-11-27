package com.example.NTT.helper;

public class checkingCondition {

    public static void checkInput(int value) throws MissInputException {
        if (value == 1 || value == 2 || value == 3 || value == 4 || value == 9) {
            System.out.println("ok to continue");
        }
        else {
            throw new MissInputException(value);
        }
    }
}
