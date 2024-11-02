package ru.job4j.ood.dip;

import ru.job4j.ood.dip.model.User;

import java.util.HashMap;
import java.util.Map;

public class TinkoffBank {

    /* Нарушение DIP: класс содержит map для хранение клиентов,
     * значит класс зависит от реализации.
     * Решение - выделение абстракции для хранилища.*/
    private Map<Integer, User> clients = new HashMap<>();

    public boolean addUser(User user) {
        if (clients.containsValue(user)) {
            return false;
        }
        return clients.put(user.getId(), user) != null;
    }

    public void process(double amount) {
        System.out.printf("Pay via tinkoff" + amount);
    }


    static class PayService {

        /*Нарушение DIP: PayService зависит от конкретного способа оплаты (от Tinkoff),
        * если захотим поменять способ оплаты, приедтся менять код.*/
        private TinkoffBank tink;

        public PayService(TinkoffBank tink) {
            this.tink = tink;
        }

        public void pay(double amount) {
            tink.process(amount);
        }
    }

}
