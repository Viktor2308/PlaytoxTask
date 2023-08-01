package org.example.service.impl;

import ch.qos.logback.classic.Logger;
import org.example.entity.Account;
import org.example.exception.MoneyLimitException;
import org.example.service.AccountService;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {

    private static final Logger log = (Logger) LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public boolean increaseTheBalance(Account account, BigDecimal money) {
          try {
              account.setMoney(account.getMoney().add(money));
              log.info("Balance account: {}, increase for: {}", account.getId(), money);
              return true;
          }catch (Exception e){
              log.error("Error increase balance account: {}, increase for: {}", account.getId(), money);
          }
          return false;
    }

    @Override
    public boolean decreaseTheBalance(Account account, BigDecimal money) throws MoneyLimitException {
        if (account.getMoney().subtract(money).signum()<0){
            log.info("Not enough money to decrease" + Thread.currentThread().getName());
            throw new MoneyLimitException("Not enough money to decrease");
        } else {
            account.setMoney(account.getMoney().subtract(money));
            log.info("Balance account: {}, decrease for: {}", account.getId(), money);
            return true;
        }
    }
}
