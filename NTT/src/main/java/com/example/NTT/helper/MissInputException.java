package com.example.NTT.helper;

public class MissInputException extends Exception {

    private final int value;

    public MissInputException(int value) {
        this.value = value;
    }

    @Override
    public String getMessage() {
        return "Invalid input: " + value + ". Please enter 1, 2, 3, or 9.";
    }
}
