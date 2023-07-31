package org.example.entity;


import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private String id;
    private BigDecimal money;
    private final Lock locker;
    private static final Logger log = (Logger) LoggerFactory.getLogger(Account.class);
    public Account(String id, BigDecimal money) {
        this.id = id;
        this.money = money;
        this.locker = new ReentrantLock();
        log.info("Create new account: {}, money: {}", id, money);
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
