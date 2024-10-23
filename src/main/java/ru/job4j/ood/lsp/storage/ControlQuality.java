package ru.job4j.ood.lsp.storage;

import ru.job4j.ood.lsp.storage.model.Food;
import ru.job4j.ood.lsp.storage.store.Store;

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

}
