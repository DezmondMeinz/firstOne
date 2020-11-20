package ru.geekbrains.homework;

public class Plate {

    public int food;


    public Plate(int food) {
        this.food = food;
    }

    boolean lessFood(int n) {
        int diff = food - n;
        if (diff < 0) return false;

        food -= n;
        return true;
    }
    void addFood(int food) {
        this.food += food;
    }

    void info() {
        System.out.println("plate: " + food);
    }
}
