package ru.job4j.ood.dip.shop;

import ru.job4j.ood.dip.model.Order;
import ru.job4j.ood.dip.model.User;

import java.util.Set;

public interface ShopStore {

    boolean saveUser(User user);
    boolean saveOrder(User user, Order order);
    Set<Order> getOrders(User user);
    Set<User> getUsers();

}
