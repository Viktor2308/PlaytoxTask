package org.example.service;

import org.example.entity.Account;
import org.example.exception.MoneyLimitException;

import java.math.BigDecimal;

public interface Transaction {

    void createTransaction(Account fromAcc, Account toAcc, BigDecimal countMoney);
    void moneyTransfer(Account fromAcc, Account toAcc, BigDecimal countMoney) throws MoneyLimitException;
}
