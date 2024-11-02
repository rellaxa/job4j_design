package ru.job4j.ood.dip.order;

import ru.job4j.ood.dip.model.Product;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOrderStore implements OrderStore {

    private Map<Integer, Product> products = new HashMap<>();

    public boolean add(Product product) {
        if (products.containsKey(product.getId())) {
            return false;
        }
        return products.put(product.getId(), product) != null;
    }

    public boolean remove(int id) {
        return products.remove(id) != null;
    }

    public void clear() {
        products.clear();
    }
}
