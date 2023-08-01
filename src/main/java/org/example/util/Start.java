package org.example.util;

import ch.qos.logback.classic.Logger;
import org.example.entity.Account;
import org.example.service.AccountService;
import org.example.service.Transaction;
import org.example.service.impl.AccountServiceImpl;
import org.example.service.impl.TransactionalImpl;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.example.util.StartUtil.*;

public class Start {

    private final ExecutorService threadPool;
    private final Transaction transaction;
    private static final Logger log = (Logger) LoggerFactory.getLogger(Start.class);

    public Start(int countThreads) {
        threadPool = Executors.newFixedThreadPool(countThreads);
        AccountService accountService = new AccountServiceImpl();
        transaction = new TransactionalImpl(accountService);
    }

    public void startTransactions(int countTransaction, int countAccounts, int startMoney, int maxMoneyTransfer) {
        List<Account> accountList = generatorAccount(countAccounts, BigDecimal.valueOf(startMoney));
        log.info("All money: {}", sumMoneyAllAccounts(accountList));
        for (int i = 0; i < countTransaction; i++) {
            threadPool.execute(() -> {
                        int[] randomTransactionalPair = generatorTransferPairs(countAccounts);
                        transaction.createTransaction(
                                accountList.get(randomTransactionalPair[0]),
                                accountList.get(randomTransactionalPair[1]),
                                generatorMoneyTransferAmount(maxMoneyTransfer)
                        );
                    }
            );
        }
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(50000, TimeUnit.SECONDS)) {
                List<Runnable> runnableList = threadPool.shutdownNow();
                log.warn("Unfinished thread: " + runnableList.stream().map(Object::toString).collect(Collectors.toList()));
            }
        } catch (InterruptedException e) {
            threadPool.shutdown();
            Thread.currentThread().interrupt();
        }
        log.info("All money after work app: {}", sumMoneyAllAccounts(accountList));
    }
}
