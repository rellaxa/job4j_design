package ru.job4j.ood.dip.order;

import ru.job4j.ood.dip.model.Product;

public interface OrderService {

    boolean addProd(Product product);
    boolean removeProd(int id);

}
