package ru.geekbrains.homework;

public class DemoApp {

    public static void main(String[] args) {

        Cat[] cats = {new Cat("Murzik", 50), new Cat("Bombilo", 50), new Cat("Snezhok", 25), new Cat("Milan", 5), new Cat("Kate", 1)};
        Plate plate = new Plate( 80);
        for (Cat cat : cats) {
            cat.eat(plate);
            cat.info();
        }
    }
}
