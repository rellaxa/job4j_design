package ru.job4j.ood.lsp.storage.model;

import java.time.LocalDate;

public class Meat extends Food {

    public Meat(String name, LocalDate createDate, LocalDate expiryDate, int price) {
        super(name, createDate, expiryDate, price);
    }

}
