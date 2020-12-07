package ru.geekbrains.homework;

public class Task_2 {

    public static void main(String[] args){
        System.out.println("Создаем справочник");
        PhoneBookForTask_2  phonebook = new PhoneBookForTask_2 ();
        System.out.println("-----------------");

        System.out.println("Fill the book");
        phonebook.add("Иванов", "223344");
        phonebook.add("Иванов", "22334411");
        phonebook.add("Петров", "22334499");
        phonebook.add("Сидоров", "22334488");
        phonebook.add("Иванов", "22334422");
        System.out.println("-//-//-//-//-//-//-//-");

        System.out.println("Get numbers");
        System.out.println("Иванов");
        System.out.println(phonebook.get("Иванов"));
        System.out.println("Петров");
        System.out.println(phonebook.get("Петров"));
        System.out.println("Сидоров");
        System.out.println(phonebook.get("Сидоров"));
        System.out.println("-//-//-//-//-//-//-//-");

        System.out.println("The note is upset");
        System.out.println("Кузнецов");
        System.out.println(phonebook.get("Кузнецов"));
        System.out.println("-//-//-//-//-//-//-//-");

        System.out.println("Try to write existing number");
        phonebook.add("Иванов", "223344");
        System.out.println("Иванов");
        System.out.println(phonebook.get("Иванов"));
    }
}
