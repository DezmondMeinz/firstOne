package ru.geekbrains.homework;

import java.util.Arrays;

public class TaskArrays1 {

    public static void main(String[] args) {
     int[] arr={0,1,0,1,1,1,0,0};
        System.out.println(Arrays.toString(arr));
     for (int k=0;k<arr.length;k++){
         if (arr[k]==0) arr[k]=1;
         else if (arr[k]==1) arr[k]=0;
     }

        System.out.println(Arrays.toString(arr));
    }
}
