package ru.job4j.ood.dip.shop;

import ru.job4j.ood.dip.model.Order;
import ru.job4j.ood.dip.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryShopStore implements ShopStore {

    private Map<User, Set<Order>> shopStore = new HashMap<>();

    @Override
    public boolean saveUser(User user) {
        if (shopStore.containsKey(user)) {
            return false;
        }
        return shopStore.put(user, new HashSet<>()) != null;
    }

    @Override
    public boolean saveOrder(User user, Order order) {
        Set<Order> orders = getOrders(user);
        if (orders.isEmpty()) {
            return false;
        }
        return orders.add(order);
    }

    @Override
    public Set<Order> getOrders(User user) {
        return shopStore.getOrDefault(user, Set.of());
    }

    @Override
    public Set<User> getUsers() {
        return shopStore.keySet();
    }
}
