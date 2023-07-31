package org.example.exception;

public class MoneyLimitException extends Exception {
    public MoneyLimitException(String massage) {
        super(massage);
    }
}