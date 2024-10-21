package ru.job4j.ood.lsp;

public class Animal {

    static class Cat extends Animal {
    }

    static class AnimalShelter {

        void accept(Animal animal) {
            System.out.println("Accept the Animal");
        }

    }

    static class CatShelter extends AnimalShelter {

        /**
         * Здесь усилено предусловие, так как метод использует более точный тип подкласса.
         * И при вызове этого метода у типа AnimalShelter будет вызван метод с типом Animal.
         * А для вызова accept с Cat, вызывающий код должен знать, что он рабоатает именно
         * с CatShelter и Cat. Это перегрузка метода а не переопределение, ибо сигнатура отличается.
         */
        void accept(Cat cat) {
            System.out.println("Accept the Cat");
        }

    }

    public static void main(String[] args) {
        AnimalShelter animalShelter = new CatShelter();
        animalShelter.accept(new Cat());
    }

}
