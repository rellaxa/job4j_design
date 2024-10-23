package ru.job4j.ood.lsp.storage.store;

import ru.job4j.ood.lsp.storage.model.Food;

public class Shop extends AbstractStore {

    public boolean accept(Food product) {
        double percent = product.getPercentOfExpiry();
        boolean rsl = false;
        if (percent >= 25 && percent <= 75) {
            rsl = true;
        } else if (percent > 75 && percent < 100) {
            product.setPrice(product.getPrice() * (1 - product.getDiscount()));
            rsl = true;
        }
        return rsl;
    }

}
