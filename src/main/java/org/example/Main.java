package org.example;

import org.example.util.Start;

public class Main {

    public static final int COUNT_ACCOUNTS = 10;
    public static final int START_MONEY = 10000;
    public static final int MAX_TRANSFER_AMOUNT = 10000;
    public static final int COUNT_THREADS = 5;
    public static final int COUNT_TRANSACTION = 10;

    public static void main(String[] args) {

        Start start = new Start(COUNT_THREADS);

        start.startTransactions(COUNT_TRANSACTION,COUNT_ACCOUNTS,START_MONEY,MAX_TRANSFER_AMOUNT);
    }
}