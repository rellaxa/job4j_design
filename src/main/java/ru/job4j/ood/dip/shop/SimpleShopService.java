package ru.job4j.ood.dip.shop;

import ru.job4j.ood.dip.order.InMemoryOrderStore;
import ru.job4j.ood.dip.order.OrderService;
import ru.job4j.ood.dip.model.Check;
import ru.job4j.ood.dip.model.Order;
import ru.job4j.ood.dip.model.Product;
import ru.job4j.ood.dip.model.User;

import java.util.*;
import java.util.logging.Logger;

public class SimpleShopService implements ShopService {

    private ShopStore shopStore;
    private OrderService orderService;
    private static final Logger LOGGER = Logger.getLogger("Shop logger");

    public SimpleShopService(ShopStore shopStore, OrderService orderService) {
        this.shopStore = shopStore;
        this.orderService = orderService;
    }

    public boolean createBucket(User user) {
        return shopStore.saveUser(user);
    }

    public boolean createOrder(User user, Order order) {
        return shopStore.saveOrder(user, order);
    }

    public boolean addProduct(User user, Order order, Product product) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            return false;
        }
        return orderService.addProd(product);
    }

    private Optional<Order> findOrder(User user, Order order) {
        return shopStore.getOrders(user).stream()
                .filter(o -> o.getId() == order.getId())
                .findFirst();
    }

    public boolean removeProduct(User user, Order order, Product product) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            return false;
        }
        return orderService.removeProd(product.getId());
    }

    public boolean removeOrder(User user, Order order) {
        Set<Order> orders = shopStore.getOrders(user);
        if (orders == null) {
            return false;
        }
        return orders.remove(order);
    }

    public Check payOrder(User user, Order order) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            LOGGER.info("Get error with " + user + " " + order);
            throw new IllegalArgumentException("Invalid order");
        }
        if (orderDB.get().isPayed()) {
            LOGGER.info("Get error with " + user + " " + order);
            throw new IllegalArgumentException("Already payed!");
        }
        orderDB.get().setPayed(true);
        return new Check((int) (System.currentTimeMillis() % 1000_000), "Payed: " + orderDB.get().getId());
    }

}