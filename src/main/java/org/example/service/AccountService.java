package org.example.service;

import org.example.entity.Account;
import org.example.exception.MoneyLimitException;

import java.math.BigDecimal;

public interface AccountService {

    boolean increaseTheBalance(Account account, BigDecimal money);
    boolean decreaseTheBalance(Account account, BigDecimal money) throws MoneyLimitException;
}
