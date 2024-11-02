package ru.job4j.ood.dip.order;

import ru.job4j.ood.dip.model.Product;

public interface OrderStore {

    boolean add(Product product);
    boolean remove(int id);
    void clear();

}
