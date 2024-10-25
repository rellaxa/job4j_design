package ru.job4j.ood.isp;

public interface Broker {

    /**
     * Нарушение ISP: не у всех брокеров есть соцсеть
     * для обсуждения акций компании.
     */
    Share buyShare();
    void sellShare();
    void discussTheStock();

    class Share {
    }

    class TinkoffInvest implements Broker {

        @Override
        public Share buyShare() {
            return new Share();
        }

        @Override
        public void sellShare() {
            System.out.println("Sell one or more shares");
        }

        @Override
        public void discussTheStock() {
            System.out.println("U can discuss the shares of company");
        }

    }

    class SberInvest implements Broker {

        @Override
        public Share buyShare() {
            return new Share();
        }

        @Override
        public void sellShare() {
            System.out.println("Sell one or more shares");
        }

        @Override
        public void discussTheStock() {
            throw new UnsupportedOperationException();
        }

    }

}
