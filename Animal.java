package ru.geekbrains.homework;

abstract class Animal {
    private final int MAX_RUN_LENGTH = 0;
    private final int MAX_SWIM_LENGTH = 0;
    private final double MAX_JUMP_HEIGTH = 0;

    abstract void run(int length);

    abstract void swim(int length);

    abstract void jump(double height);
}

