package org.example.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.entity.Account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StartUtil {

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

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
        return BigDecimal.valueOf(random.nextInt(1, maxTransferAmount));
    }

    public static int[] generatorTransferPairs(int maxAccounts) {
        int one = 0;
        int two = 0;
        while (one == two) {
            one = random.nextInt(1,maxAccounts);
            two = random.nextInt(1,maxAccounts );
        }
        return new int[]{one, two};
    }

    public static int sumMoneyAllAccounts(List<Account> accounts) {
        int sum = 0;
        for (Account account : accounts) {
            sum += account.getMoney().intValue();
        }
        return sum;
    }

    public static int getTimeThreadSleep() {
        return random.nextInt(1000, (2000 + 1));
    }
}
