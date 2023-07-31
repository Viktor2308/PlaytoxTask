package org.example;

import org.example.entity.Account;

import java.math.BigDecimal;
import java.util.List;

import static org.example.util.StartUtil.generatorAccount;

public class Main {

    public static final int COUNT_ACCOUNT = 10;
    public static final BigDecimal START_MONEY = BigDecimal.valueOf(10000);
    public static final int MAX_TRANSFER_AMOUNT = 10000;

    public static void main(String[] args) {


        List<Account> accountList = generatorAccount(COUNT_ACCOUNT, START_MONEY);
        System.out.println(accountList);
    }
}