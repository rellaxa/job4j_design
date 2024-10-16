package ru.job4j.ood.ocp;


public class TransportFactory {

    public static class Car {

        String type;
        String wheels;

        public Car(String type, String wheels) {
            this.type = type;
            this.wheels = wheels;
        }

        public void drive() {
        }

    }

    /**
     * Наследуемый класс нарушает принцип SRP, потому что наследуется
     * ненужное состояние объекта предка.
     * Самолет не является автомобилем (отношение is A наследование).
     */
    public static class Plane extends Car {

        public Plane(String type, String wheels) {
            super(type, wheels);
        }

        public void fly() {
        }

    }

    /**
     * В зависимости от типа машины реализация колес отличается,
     * что нарушает принцип SRP. Метод не должен зависеть от конкртной реализации колес.
     */
    public static Car createCar(String message) {
        Car car = null;
        if (message.contains("tractor")) {
            String wheels = "2 big & 2 small wheels";
            car = new Car(message, wheels);
        } else if (message.contains("truck")) {
            String wheels = "10 wheels";
            car = new Car(message, wheels);
        }
        return car;
    }

    /**
     * Метод возвращает объект Plane. А если мы захотим создать яхту,
     * то придется создавать отдельный метод createYacht(), что нарушает SRP.
     * Возвращаемые типы методов должны быть абстракциями.
     */
    public static Plane createPlane() {
        return new Plane("", "");
    }

}
