package org.example.service;

import org.example.entity.Account;
import org.example.exception.MoneyLimitException;

import java.math.BigDecimal;

public interface AccountService {

    void increaseTheBalance(Account account, BigDecimal money);
    void decreaseTheBalance(Account account, BigDecimal money) throws MoneyLimitException;
}
