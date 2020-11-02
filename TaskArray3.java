package ru.geekbrains.homework;

import java.util.Arrays;

public class TaskArray3 {


    public static void main(String[] args) {
     int[] a={1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

     for (int k=0;k<a.length;k++){
      if(a[k]<6) a[k]=a[k]*2;
     }
        System.out.println(Arrays.toString(a));
    }
}
