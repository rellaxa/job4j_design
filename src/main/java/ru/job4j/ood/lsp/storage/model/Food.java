package ru.job4j.ood.lsp.storage.model;

import java.time.LocalDate;

public class Food {

    private final String name;
    private final LocalDate createDate;
    private final LocalDate expiryDate;
    private double price;
    private final double discount = 0.20;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, int price) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public double getPercentOfExpiry() {
        long totalLife = expiryDate.toEpochDay() - createDate.toEpochDay();
        long daysPassed = LocalDate.now().toEpochDay() - createDate.toEpochDay();
        return (double) daysPassed / totalLife * 100;
    }

}