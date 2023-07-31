package org.example.service.impl;

import ch.qos.logback.classic.Logger;
import org.example.entity.Account;
import org.example.exception.MoneyAmountException;
import org.example.exception.MoneyLimitException;
import org.example.service.Transaction;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class TransactionalImpl implements Transaction {
    private static final Logger log = (Logger) LoggerFactory.getLogger(TransactionalImpl.class);

    @Override
    public void createTransaction(Account fromAcc, Account toAcc, BigDecimal countMoney) {
        log.info("Start new transaction from: " + fromAcc.getId() +
                ", to: " + toAcc.getId() +
                ", transaction amount" + countMoney +
                ", threat: " + Thread.currentThread().getName());
        try{
            if(countMoney == null || countMoney.signum() <= 0){
                throw new MoneyAmountException("Transfer amount is not correct");
            }
            if(fromAcc.getMoney().compareTo(countMoney) < 0){
                throw new MoneyLimitException("Not enough money to transfer");
            }
            moneyTransfer(fromAcc,toAcc,countMoney);
            log.info("Transaction complete, from: " + fromAcc.getId() +
                    ", to: " + toAcc.getId() +
                    ", transaction amount" + countMoney +
                    ", threat: " + Thread.currentThread().getName());
        } catch (MoneyAmountException e) {
            log.warn("Transfer money amount is not correct. Transaction from: " + fromAcc.getId() +
                    ", to: " + toAcc.getId() +
                    ", transaction amount" + countMoney +
                    ", threat: " + Thread.currentThread().getName()+
                    " is canceled!");
        } catch (MoneyLimitException e){
            log.warn("Not enough money to transfer. Transaction from: " + fromAcc.getId() +
                    ", to: " + toAcc.getId() +
                    ", transaction amount" + countMoney +
                    ", threat: " + Thread.currentThread().getName()+
                    " is canceled!");
        }
    }

    @Override
    public void moneyTransfer(Account fromAcc, Account toAcc, BigDecimal countMoney){
        log.info("Start transfer money from: " + fromAcc.getId() +
                ", to: " + toAcc.getId() +
                ", transaction amount" + countMoney +
                ", threat: " + Thread.currentThread().getName());

    }
}
