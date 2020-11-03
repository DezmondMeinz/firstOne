package ru.geekbrains.homework;

import java.util.Scanner;

public class GameNumber {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int k=0;
        int an=0;
        System.out.println("Угадайте число от 0 до 9");
        int number = (int)(Math.random()*9);// вроде диапазон от 0..9
        //System.out.println(number);
        System.out.println("У Вас есть 3 попытки:");
        while (true) {
            int input = sc.nextInt();
            if (input > 9) {
                System.out.println("Введите число меньше от 0 до 9!!");
                input = sc.nextInt();
            }
            if (input<0){
                System.out.println("Введите число меньше от 0 до 9!!");
                input = sc.nextInt();
            }
            if (input>number){
                System.out.println("Загаданное число меньше, попробуй ёще раз!");
                k++;
            }
            if (input<number){
                System.out.println("Загаданное число больше, попробуй ёще раз!");
                k++;
            }
            if (input == number) {
                System.out.println("Угадали!!");
                System.out.println("Хотите еще раз сыграть? 1-да,0-нет");
                an=sc.nextInt();
                if (an==0) break;
                if (an==1) {
                    System.out.println("Что ж...вводите,тоже 3 попытки:");
                    k=0;
                    number = (int)(Math.random()*9);
                    //System.out.println(number);
                }
            }
            if (k>2){
                System.out.println("Закончились попытки T_T");
                System.out.println("Хотите еще раз сыграть? 1-да,0-нет");
                an=sc.nextInt();
                if (an==0) break;
                if (an==1) {
                    System.out.println("Что ж...вам дано 3 попытки:");
                    k=0;
                }
            }
        }
        sc.close();
    }
}
