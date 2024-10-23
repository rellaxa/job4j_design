package ru.job4j.ood.lsp.storage.store;

import ru.job4j.ood.lsp.storage.model.Food;

import java.util.List;

public interface Store {

    boolean addFood(Food product);

    List<Food> getStoreProducts();

}
