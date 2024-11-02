package ru.job4j.ood.lsp.storage.store;

import ru.job4j.ood.lsp.storage.model.Food;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractStore implements Store {

    protected List<Food> products = new ArrayList<>();

    public boolean addFood(Food product) {
        boolean rsl = false;
        if (accept(product)) {
            rsl = products.add(product);
        }
        return rsl;
    }

    abstract public boolean accept(Food product);

    public List<Food> getStoreProducts() {
        return List.copyOf(products);
    }

    @Override
    public void deleteProducts() {
        products.clear();
    }
}
