package org.example.service;

import org.example.entity.Account;

import java.math.BigDecimal;

public interface Transaction {
    void startTransactions(int countTransaction);
    boolean createTransaction(Account fromAcc, Account toAcc, BigDecimal countMoney);
}
