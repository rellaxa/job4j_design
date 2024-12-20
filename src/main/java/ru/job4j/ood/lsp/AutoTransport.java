package ru.job4j.ood.lsp;

class AutoTransport {

    protected float fuel;

    public AutoTransport(float fuel) {
        this.fuel = fuel;
    }

    public void move(float km) {
        if (km < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (fuel < 0) { /* <= предусловие */
            throw new IllegalArgumentException("Need more fuel!");
        }
        /* other logic */
    }

    static class Bus extends AutoTransport {

        public Bus(float fuel) {
            super(fuel);
        }

        @Override
        public void move(float km) {
            if (km < 0) {
                throw new IllegalArgumentException("Invalid distance!");
            }
            if (fuel < 5) { /* условие усилено */
                throw new IllegalArgumentException("Need more fuel!");
            }
            /* other logic */
        }

    }

    public static class FirstRule {

        public static void main(String[] args) {
            AutoTransport bus = new Bus(3);
            bus.move(2);
        }

    }

}
