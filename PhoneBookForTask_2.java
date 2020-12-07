package ru.geekbrains.homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PhoneBookForTask_2 {
    private HashMap<String, List<String>> book;

    public PhoneBookForTask_2(){
        this.book = new HashMap<>();
    }

    public void add(String surname, String number){
        if(book.containsKey(surname)){
            List<String> numbers = book.get(surname);
            if(!numbers.contains(number)){
                numbers.add(number);
                System.out.println(String.format("Number %s added for surname %s", number, surname));
            } else {
                System.out.println(String.format("Number %s is already existing fot surname %s", number, surname));
            }
        } else {
            book.put(surname, new ArrayList<>(Arrays.asList(number)));
            System.out.println(String.format("Number %s addded for surname %s", number, surname));
        }
    }

    public List<String> get(String surname){
        if(book.containsKey(surname)){
            return book.get(surname);
        } else {
            System.out.println(String.format("The book has not such info %s", surname));
            return new ArrayList<>();
        }
    }
}
