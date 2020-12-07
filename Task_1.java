package ru.geekbrains.homework;

import java.util.*;

public class Task_1{

    public static void main(String[] args){
        List<String> words = Arrays.asList(
                "LongSword", "Palash", "Spear", "Axe", "Pistol",
                "Arrow", "Stuff", "Hunter", "Axe", "Pistol",
                "Palash", "Spear", "Axe", "Stuff", "Stuff",
                "Spear", "Axe", "Stuff", "Stuff", "Pistol"
        );

        Set<String> unique = new HashSet<String>(words);

        System.out.println("Massiv:");
        System.out.println(words.toString());
        System.out.println("Unique words");
        System.out.println(unique.toString());
        System.out.println("How often we meet words");
        for (String key : unique) {
            System.out.println(key + ": " + Collections.frequency(words, key));
        }
    }
}
