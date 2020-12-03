package ru.geekbrains.homework;

public class ArrSizeException extends RuntimeException{
    public ArrSizeException() {
        super("Размер массива некорректный!!!");
    }
}
