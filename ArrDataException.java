package ru.geekbrains.homework;

public class ArrDataException extends RuntimeException {
    public ArrDataException(String message) {
        super("Некорректные Данные "+message);
    }
}
