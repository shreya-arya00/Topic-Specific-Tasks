package com.bobocode.fp;

import com.bobocode.data.Accounts;
import com.bobocode.fp.exception.AccountNotFoundException;
import com.bobocode.fp.function.AccountProvider;
import com.bobocode.fp.function.AccountService;
import com.bobocode.fp.function.CreditAccountProvider;
import com.bobocode.model.Account;
import com.bobocode.model.CreditAccount;
import com.bobocode.util.ExerciseNotCompletedException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class CrazyOptionals {

    public static Optional<String> optionalOfString(@Nullable String text) {
        return Optional.ofNullable(text);
    }

    public static void deposit(AccountProvider accountProvider, BigDecimal amount) {
        accountProvider.getAccount()
                .ifPresent(account -> account.setBalance(account.getBalance().add(amount)));
    }

    public static Optional<Account> optionalOfAccount(@Nonnull Account account) {
        return Optional.of(account);
    }

    public static Account getAccount(AccountProvider accountProvider, Account defaultAccount) {
        return accountProvider.getAccount().orElse(defaultAccount);
    }

    public static void processAccount(AccountProvider accountProvider, AccountService accountService) {
        accountProvider.getAccount()
                .ifPresent(accountService::processAccount);
        if (!accountProvider.getAccount().isPresent()) {
            accountService.processWithNoAccount();
        }
    }

    public static Account getOrGenerateAccount(AccountProvider accountProvider) {
        return accountProvider.getAccount().orElseGet(Accounts::generateAccount);
    }

    public static Optional<BigDecimal> retrieveBalance(AccountProvider accountProvider) {
        return accountProvider.getAccount().map(Account::getBalance);
    }

    public static Account getAccount(AccountProvider accountProvider) {
        return accountProvider.getAccount()
                .orElseThrow(() -> new AccountNotFoundException("No Account provided!"));
    }

    public static Optional<BigDecimal> retrieveCreditBalance(CreditAccountProvider accountProvider) {
        return accountProvider.getCreditAccount().map(CreditAccount::getCreditBalance);
    }

    public static Optional<Account> retrieveAccountGmail(AccountProvider accountProvider) {
        return accountProvider.getAccount()
                .filter(account -> account.getEmail().toLowerCase().endsWith("@gmail.com"));
    }

    public static Account getAccountWithFallback(AccountProvider accountProvider, AccountProvider fallbackProvider) {
        return accountProvider.getAccount()
                .orElseGet(() -> fallbackProvider.getAccount()
                        .orElseThrow(() -> new NoSuchElementException("No Account provided!")));
    }

    public static Account getAccountWithMaxBalance(List<Account> accounts) {
        return accounts.stream()
                .max(Comparator.comparing(Account::getBalance))
                .orElseThrow(NoSuchElementException::new);
    }

    public static OptionalDouble findMinBalanceValue(List<Account> accounts) {
        return accounts.stream()
                .mapToDouble(account -> account.getBalance().doubleValue())
                .min();
    }

    public static void processAccountWithMaxBalance(List<Account> accounts, AccountService accountService) {
        Account accountWithMaxBalance = getAccountWithMaxBalance(accounts);
        accountService.processAccount(accountWithMaxBalance);
    }

    public static double calculateTotalCreditBalance(List<CreditAccount> accounts) {
        return accounts.stream()
                .mapToDouble(CreditAccount::getCreditBalance)
                .sum();
    }
}
