package org.example.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.entity.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StartUtil {

    public static String generatorId() {
        int length = 12;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    public static List<Account> generatorAccount(int countAccounts, BigDecimal startMoney) {
        List<Account> accountList = new ArrayList<>();
        for (int i = 0; i < countAccounts; i++) {
            accountList.add(new Account(generatorId(), startMoney));
        }
        return accountList;
    }

    public static BigDecimal generatorMoneyTransferAmount(int maxTransferAmount) {
        Random random = new Random();
        return BigDecimal.valueOf(random.ints(1, (maxTransferAmount + 1)).findFirst().getAsInt());
    }

    public static int[] generatorTransferPairs(int maxAccounts) {
        Random random = new Random();
        int one = random.ints(1, (maxAccounts)).findFirst().getAsInt();
        int two = random.ints(1, (maxAccounts)).findFirst().getAsInt();
        if (one == two) {
            generatorTransferPairs(maxAccounts);
        }
        return new int[]{one, two};
    }

    public static int sumMoneyAllAccounts(List<Account> accounts){
        int sum = 0;
        for(Account account : accounts){
            sum += account.getMoney().intValue();
        }
        return sum;
    }
}
