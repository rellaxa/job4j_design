package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.storage.model.Food;
import ru.job4j.ood.lsp.storage.model.Fruit;
import ru.job4j.ood.lsp.storage.model.Meat;
import ru.job4j.ood.lsp.storage.store.Shop;
import ru.job4j.ood.lsp.storage.store.Store;
import ru.job4j.ood.lsp.storage.store.Trash;
import ru.job4j.ood.lsp.storage.store.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distributeFood(Food product) {
        for (Store store : stores) {
            if (store.addFood(product)) {
                break;
            }
        }
    }

    public void resortProducts() {
        List<Food> prods = new ArrayList<>();
        for (Store store : stores) {
            prods.addAll(store.getStoreProducts());
            store.deleteProducts();
        }
        prods.forEach(this::distributeFood);
    }
}
