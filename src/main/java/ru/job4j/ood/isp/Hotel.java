package ru.job4j.ood.isp;

public interface Hotel {

    /**
     * Нарушение ISP: не все отели предоставляет данный список услуг.
     */
    Room getRoom();
    void getCleaningForRoom();
    void getFoodInRoom();

    class Room {
    }

    class LuxuryHotel implements  Hotel {

        @Override
        public Room getRoom() {
            return new Room();
        }

        @Override
        public void getCleaningForRoom() {
            System.out.println("Clear room and no stealing");
        }

        @Override
        public void getFoodInRoom() {
            System.out.println("Get breakfast in room");
        }

    }

    class UsualHotel implements Hotel {

        @Override
        public Room getRoom() {
            return new Room();
        }

        @Override
        public void getCleaningForRoom() {
            System.out.println("Probably stealing");
        }

        @Override
        public void getFoodInRoom() {
            throw new UnsupportedOperationException();
        }

    }

}
