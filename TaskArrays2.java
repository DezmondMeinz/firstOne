package ru.geekbrains.homework;

import java.util.Arrays;
import java.util.Scanner;

public class TaskArrays2 {

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
       int[] arr=new int[8];
        System.out.println("Enter the array elements:");
       for (int k=0;k<arr.length;k++){
           arr[k]=sc.nextInt();
       }
        System.out.println("Array:");
        System.out.println(Arrays.toString(arr));
        sc.close();
    }
}
