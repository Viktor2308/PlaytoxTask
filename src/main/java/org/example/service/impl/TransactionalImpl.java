package org.example.service.impl;

import org.example.entity.Account;
import org.example.service.Transaction;

import java.math.BigDecimal;

public class TransactionalImpl implements Transaction {


    @Override
    public void startTransactions(int countTransaction) {

    }

    @Override
    public boolean createTransaction(Account fromAcc, Account toAcc, BigDecimal countMoney) {
        return false;
    }
}
