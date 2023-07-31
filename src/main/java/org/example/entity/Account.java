package org.example.entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.example.Util.StartUtil.START_MONEY;

public class Account {
    private String id;
    private BigDecimal money;
    private final Lock locker;
    private final Logger log = LoggerFactory.getLogger(Account.class);

    public Account(String id) {
        this.id = id;
        this.money = START_MONEY;
        this.locker = new ReentrantLock();
        log.info("Create new account: {}", id);
    }

    public String getId() {
        return id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account: " + id + ", money: " + money;
    }


}
