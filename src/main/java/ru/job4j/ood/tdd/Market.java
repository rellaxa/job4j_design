package ru.job4j.ood.tdd;

import java.util.List;

/**
 * Класс Market содержит список продуктов на продажу и может добавлять продукты
 * в свой ассортимент. Но не должен отвечать за производство и транспортировку
 * продукции, что нарушает SRP.
 */

public class Market {

    private List<Object> products;

    public Market(List<Object> products) {
        this.products = products;
    }

    public boolean addProduct(Object product) {
        return products.add(product);
    }

    public boolean transportTheProducts() {
        return false;
    }

    public Object produceProduct() {
        return new Object();
    }

}
