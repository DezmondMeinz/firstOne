package ru.geekbrains.homework;

import java.util.Arrays;
import java.util.Scanner;

public class TaskArrays5 {

    public static void main(String[] args) {
        int max=0,min=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("Введите кол-во элементов:");
        int k=sc.nextInt();
       int[] a=new int[k];
       System.out.println("Введите элементы:");
       for (int i=0;i<a.length;i++){
           a[i]=sc.nextInt();
       }
        System.out.println("Ваш массив: "+ Arrays.toString(a));
       for (int i=0;i<a.length;i++){
           if (a[i]>max) {
               max=a[i];
           }
           if (a[i]<min){
               min=a[i];
           }

       }
        System.out.println("Max: "+max);
        System.out.println("Min: "+min);
        sc.close();
    }
}
