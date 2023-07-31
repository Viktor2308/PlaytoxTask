package org.example.Util;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;

public class StartUtil {

    public static final int COUNT_ACCOUNT = 5;
    public static final BigDecimal START_MONEY = BigDecimal.valueOf(10000);
    public static String generatorId() {
        int length = 12;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

}
