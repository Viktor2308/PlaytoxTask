package org.example;

import org.example.entity.Account;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.example.util.StartUtil.*;

public class Main {

    public static final int COUNT_ACCOUNTS = 10;
    public static final BigDecimal START_MONEY = BigDecimal.valueOf(10000);
    public static final int MAX_TRANSFER_AMOUNT = 10000;

    public static void main(String[] args) {
        BigDecimal bigDecimal = BigDecimal.valueOf(1);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(2);
        System.out.println(bigDecimal.compareTo(bigDecimal1));

//        List<Account> accountList = generatorAccount(COUNT_ACCOUNTS, START_MONEY);
//        System.out.println(accountList);
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Arrays.toString(generatorTransferPairs(COUNT_ACCOUNTS)));
//            System.out.println(generatorMoneyTransferAmount(MAX_TRANSFER_AMOUNT));
//        }
    }
}