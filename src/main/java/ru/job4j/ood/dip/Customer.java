package ru.job4j.ood.dip;

import ru.job4j.ood.dip.model.Order;

public class Customer {

    /* Нарушение OCP и DIP: работа метода зависит от конкретного класса OrderCheck*/
    public boolean buyProducts(Order order) {
        if (order.isPayed()) {
            return false;
        }
        var checkOrder = new OrderCheck();
        return checkOrder.check(order);
    }

    static class OrderCheck {

        boolean check(Order order) {
            return false;
        }
    }

}
