package org.example.service.impl;

import ch.qos.logback.classic.Logger;
import org.example.entity.Account;
import org.example.exception.MoneyAmountException;
import org.example.exception.MoneyLimitException;
import org.example.service.AccountService;
import org.example.service.Transaction;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

import static org.example.util.StartUtil.getTimeThreadSleep;

public class TransactionalImpl implements Transaction {
    private final AccountService accountService;
    private static final Logger log = (Logger) LoggerFactory.getLogger(TransactionalImpl.class);

    public TransactionalImpl(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public void createTransaction(Account fromAcc, Account toAcc, BigDecimal countMoney) {
        log.info("Start new transaction from: " + fromAcc.getId() +
                ", to: " + toAcc.getId() +
                ", transaction amount" + countMoney +
                ", threat: " + Thread.currentThread().getName());
        try {
            if (countMoney == null || countMoney.signum() <= 0) {
                throw new MoneyAmountException("Transfer amount is not correct");
            }

            moneyTransfer(fromAcc, toAcc, countMoney);

            Thread.sleep(getTimeThreadSleep());

            log.info("Transaction complete, from: " + fromAcc.getId() +
                    ", to: " + toAcc.getId() +
                    ", transaction amount" + countMoney +
                    ", threat: " + Thread.currentThread().getName());


        } catch (MoneyAmountException e) {
            log.warn("Transfer money amount is not correct. Transaction from: " + fromAcc.getId() +
                    ", to: " + toAcc.getId() +
                    ", transaction amount" + countMoney +
                    ", threat: " + Thread.currentThread().getName() +
                    " is canceled!");
        } catch (MoneyLimitException e) {
            log.warn("Not enough money to transfer. Transaction from: " + fromAcc.getId() +
                    ", to: " + toAcc.getId() +
                    ", transaction amount" + countMoney +
                    ", threat: " + Thread.currentThread().getName() +
                    " is canceled!");
        } catch (InterruptedException e){
            log.error("Interrupted exception. Transaction from: " + fromAcc.getId() +
                    ", to: " + toAcc.getId() +
                    ", transaction amount" + countMoney +
                    ", threat: " + Thread.currentThread().getName() +
                    " is canceled!");
        }
    }

    @Override
    public void moneyTransfer(Account fromAcc, Account toAcc, BigDecimal countMoney) throws MoneyLimitException {
        log.info("Start transfer money from: " + fromAcc.getId() +
                ", to: " + toAcc.getId() +
                ", transaction amount" + countMoney +
                ", threat: " + Thread.currentThread().getName());
        try {
            fromAcc.lock();
            toAcc.lock();
            accountService.decreaseTheBalance(fromAcc, countMoney);
            accountService.increaseTheBalance(toAcc, countMoney);
        } catch (MoneyLimitException e) {
            log.warn("Not enough money to transfer. Transfer from: " + fromAcc.getId() +
                    ", to: " + toAcc.getId() +
                    ", transaction amount" + countMoney +
                    ", threat: " + Thread.currentThread().getName() +
                    " is canceled!");
        } finally {
            fromAcc.unlock();
            toAcc.unlock();
        }
    }
}
