package ru.job4j.ood.srp;

/**
 * Класс BankAccount по сути модель, но мы запихули перевод денег
 * на другой аккаунт и перевод в другую валюту баланса, что нарушает SRP
 * Эту функциональность лучше делегировать на BankService
 */

public class BankAccount {

    private int id;
    private String name;
    private int rubBalance;

    public BankAccount(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return rubBalance;
    }

    public void setBalance(int balance) {
        this.rubBalance = balance;
    }

    public boolean transfer(BankAccount anotherAcc) {
        return false;
    }

    public void currencyExchange() {
    }

}
