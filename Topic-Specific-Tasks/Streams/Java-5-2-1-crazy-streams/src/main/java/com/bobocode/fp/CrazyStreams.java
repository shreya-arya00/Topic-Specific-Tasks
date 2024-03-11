package com.bobocode.fp;

import com.bobocode.model.Account;
import com.bobocode.util.ExerciseNotCompletedException;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@link CrazyStreams} is an exercise class. Each method represents some operation with a collection of accounts that
 * should be implemented using Stream API.
 *
 * @author Taras Boychuk
 */
@AllArgsConstructor
public class CrazyStreams {
    private Collection<Account> accounts;

    public Optional<Account> findRichestPerson() {
        return accounts.stream()
                .max(Comparator.comparing(Account::getBalance));
    }

    public List<Account> findAccountsByBirthdayMonth(Month birthdayMonth) {
        return accounts.stream()
                .filter(account -> account.getBirthday().getMonth() == birthdayMonth)
                .collect(Collectors.toList());
    }

    public Map<Boolean, List<Account>> partitionMaleAccounts() {
        return accounts.stream()
                .collect(Collectors.partitioningBy(account -> account.getGender() == Gender.MALE));
    }

    public Map<String, List<Account>> groupAccountsByEmailDomain() {
        return accounts.stream()
                .collect(Collectors.groupingBy(account -> account.getEmail().split("@")[1]));
    }

    public int getNumOfLettersInFirstAndLastNames() {
        return accounts.stream()
                .mapToInt(account -> account.getFirstName().length() + account.getLastName().length())
                .sum();
    }

    public BigDecimal calculateTotalBalance() {
        return accounts.stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Account> sortByFirstAndLastNames() {
        return accounts.stream()
                .sorted(Comparator.comparing(Account::getFirstName).thenComparing(Account::getLastName))
                .collect(Collectors.toList());
    }

    public boolean containsAccountWithEmailDomain(String emailDomain) {
        return accounts.stream()
                .anyMatch(account -> account.getEmail().endsWith("@" + emailDomain));
    }

    public BigDecimal getBalanceByEmail(String email) {
        return accounts.stream()
                .filter(account -> account.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .map(Account::getBalance)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find Account by email=" + email));
    }

    public Map<Long, Account> collectAccountsById() {
        return accounts.stream()
                .collect(Collectors.toMap(Account::getId, Function.identity()));
    }

    public Map<String, BigDecimal> collectBalancesByEmailForAccountsCreatedOn(int year) {
        return accounts.stream()
                .filter(account -> account.getCreationDate().getYear() == year)
                .collect(Collectors.toMap(Account::getEmail, Account::getBalance));
    }

    public Map<String, Set<String>> groupFirstNamesByLastNames() {
        return accounts.stream()
                .collect(Collectors.groupingBy(Account::getLastName,
                        Collectors.mapping(Account::getFirstName, Collectors.toSet())));
    }

    public Map<Month, String> groupCommaSeparatedFirstNamesByBirthdayMonth() {
        return accounts.stream()
                .collect(Collectors.groupingBy(Account::getBirthdayMonth,
                        Collectors.mapping(Account::getFirstName, Collectors.joining(", "))));
    }

    public Map<Month, BigDecimal> groupTotalBalanceByCreationMonth() {
        return accounts.stream()
                .collect(Collectors.groupingBy(Account::getCreationMonth,
                        Collectors.reducing(BigDecimal.ZERO, Account::getBalance, BigDecimal::add)));
    }

    public Map<Character, Long> getCharacterFrequencyInFirstNames() {
        return accounts.stream()
                .flatMap(account -> account.getFirstName().chars().mapToObj(c -> (char) c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map<Character, Long> getCharacterFrequencyIgnoreCaseInFirstAndLastNames(int nameLengthBound) {
        return accounts.stream()
                .filter(account -> account.getFirstName().length() >= nameLengthBound &&
                        account.getLastName().length() >= nameLengthBound)
                .flatMap(account -> (account.getFirstName() + account.getLastName()).toLowerCase().chars().mapToObj(c -> (char) c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
