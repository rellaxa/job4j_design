package ru.job4j.ood.dip.shop;

import ru.job4j.ood.dip.model.Check;
import ru.job4j.ood.dip.model.Order;
import ru.job4j.ood.dip.model.Product;
import ru.job4j.ood.dip.model.User;

public interface ShopService {

    boolean createBucket(User user);
    boolean createOrder(User user, Order order);
    boolean addProduct(User user, Order order, Product product);
    boolean removeProduct(User user, Order order, Product product);
    boolean removeOrder(User user, Order order);
    Check payOrder(User user, Order order);

}
