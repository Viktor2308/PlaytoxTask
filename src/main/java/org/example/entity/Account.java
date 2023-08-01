package org.example.entity;


import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private final String id;
    private BigDecimal money;
    private final Lock locker;
    private static final Logger log = (Logger) LoggerFactory.getLogger(Account.class);
    public Account(String id, BigDecimal money) {
        this.id = id;
        this.money = money;
        this.locker = new ReentrantLock();
        log.info("Create new account: {}, money: {}", id, money);
    }

    public void lock(){
        locker.lock();
    }
    public  void unlock(){
        locker.unlock();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(money, account.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, money);
    }
}
