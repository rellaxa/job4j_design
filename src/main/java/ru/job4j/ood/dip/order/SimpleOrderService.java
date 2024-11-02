package ru.job4j.ood.dip.order;

import ru.job4j.ood.dip.model.Product;

public class SimpleOrderService implements OrderService {

    private OrderStore orderStore;

    public SimpleOrderService(OrderStore orderStore) {
        this.orderStore = orderStore;
    }

    public boolean addProd(Product product) {
        return orderStore.add(product);
    }

    public boolean removeProd(int id) {
        return orderStore.remove(id);
    }
}
