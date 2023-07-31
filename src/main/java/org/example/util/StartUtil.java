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

    public static List<Account> generatorAccount(int count, BigDecimal money) {
        List<Account> accountList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            accountList.add(new Account(generatorId(), money));
        }
        return accountList;
    }

    public static int generatorMoneyTransferAmount(int maxTransferAmount) {
        Random random = new Random();
        return random.ints(1, (maxTransferAmount + 1)).findFirst().getAsInt();
    }

    public static int[] generatorTransferPairs(int maxAccounts) {
        Random random = new Random();
        int one = random.ints(1, (maxAccounts + 1)).findFirst().getAsInt();
        int two = random.ints(1, (maxAccounts + 1)).findFirst().getAsInt();
        if (one == two) {
            generatorTransferPairs(maxAccounts);
        }
        return new int[]{one, two};
    }
}
