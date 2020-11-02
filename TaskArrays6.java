package ru.geekbrains.homework;

import java.util.Arrays;
import java.util.Scanner;

public class TaskArrays6 {

    public static boolean checkBalance(int[] a){
     int sum=0;
     int rightSum=0;
     boolean balance=false;
      for (int i=0;i<a.length;i++){
          sum=sum+a[i];
      }
        for (int i=0;i<a.length;i++){
            rightSum=rightSum+a[i];
            if (sum-rightSum==rightSum) balance=true;
        }
      return balance;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Введите кол-во элементов:");
        int k=sc.nextInt();
        int[] arr=new int[k];
        System.out.println("Введите элементы:");
        for (int i=0;i<arr.length;i++){
            arr[i]=sc.nextInt();
        }
        System.out.println("Ваш массив: "+ Arrays.toString(arr));
        System.out.println("Есть ли баланс:");
        System.out.println(checkBalance(arr));
        sc.close();
    }
}
