package ru.job4j.ood.lsp;

public class Account {

    protected int capital;

    public void setCapital(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Нельзя положить на счет меньше 0");
        }
        this.capital = money;
    }

    public double getInterest(double sum, int month, int rate) {
        /* предусловие */
        if (sum < 0 || month > 12 || month < 1 || rate < 0) {
            throw new IllegalArgumentException("Некорректные данные");
        }

        double result = sum;
        for (int i = 0; i < month; i++) {
            result += result * rate / 100;
        }
        /* постусловие */
        if (sum >= 1000) {
            result += 100; // добавляем бонус
        }
        return result;
    }

    static class MicroAccount extends Account {

        /* Усиление предусловия.*/
        @Override
        public void setCapital(int money) {
            if (money < 0) {
                throw new IllegalArgumentException("Нельзя положить на счет меньше 0");
            }
            if (money > 100) {
                throw new IllegalArgumentException("Нельзя положить на счет больше 100");
            }
            this.capital = money;
        }

        @Override
        public double getInterest(double sum, int month, int rate) {

            if (sum < 0 || month > 12 || month < 1 || rate < 0) {
                throw new IllegalArgumentException("Некорректные данные");
            }

            double result = sum;
            for (int i = 0; i < month; i++) {
                result += result * rate / 100;
            }
            /* Ослабено постусловие */
            return result;
        }

    }

    public static void main(String[] args) {
        Account acc = new MicroAccount();
        acc.setCapital(200);
        double sum = acc.getInterest(1000, 1, 10);
        if (sum != 1200) {
            throw new RuntimeException("Неожиданная сумма при вычислениях");
        }
    }

}

