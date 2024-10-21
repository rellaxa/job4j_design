package ru.job4j.ood.lsp;

public class Rectangle {

    int height;
    int weight;

    void setHeight(int height) {
        this.height = height;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    int getArea() {
        return height * weight;
    }

    static class Square extends Rectangle {

        /**
         * Переопределение логики изменения значений родительского класса,
         * что нарушает принцип LSP. Нарушение Контрактов LSP:
         * В базовом классе Rectangle стороны прямоугольника устанавливаются независимо друг от друга.
         * Однако в классе Square они вызывают setSide(), устанавливающий ширину и высоту равными,
         * что усиливает предусловие.
         * Высота и ширина в Square всегда равны, что не соответсвует поведению базового класса.
         * Нарушено состояние объекта потомка, потому что в нем не соблюдено условие предка.
         */
        @Override
        void setHeight(int height) {
            this.setSide(height);
        }

        @Override
        void setWeight(int weight) {
            this.setSide(weight);
        }

        void setSide(int size) {
            super.height = size;
            super.weight = size;
        }

    }

    public static void main(String[] args) {
        Rectangle rectangle = new Square();
        rectangle.setHeight(4);
        rectangle.setWeight(3);
        if (rectangle.getArea() != 12) {
            throw new RuntimeException("Некорректная площадь");
        }
    }

}
