package ru.geekbrains.homework;

import java.util.Arrays;
import java.util.Scanner;

public class TaskArray4 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Введите кол-во  столбцов и строк:");
        int l=sc.nextInt();
        int[][]m=new int[l][l];
        System.out.println("Заполните на диагональ:");
        for (int i=0;i<m.length;i++){
           for (int j=0;j<m[i].length;j++){
            if (i==j) m[i][j]=1;
            else if (i+j==m.length-1) m[i][j]=1;
           }
        }

        for (int k = 0; k < m.length; k++) {
            System.out.println(Arrays.toString(m[k]));
        }
        sc.close();
    }
}
