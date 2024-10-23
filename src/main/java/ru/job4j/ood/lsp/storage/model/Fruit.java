package ru.job4j.ood.lsp.storage.model;

import java.time.LocalDate;

public class Fruit extends Food {

    public Fruit(String name, LocalDate createDate, LocalDate expiryDate, int price) {
        super(name, createDate, expiryDate, price);
    }

}
