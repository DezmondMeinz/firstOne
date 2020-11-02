package ru.geekbrains.homework;

import java.util.Scanner;

public class TaskMethods {

public static int Calculate(int a,int b,int c,int d){
return a*(b+(c/d));
}
public static boolean Check(int a,int b){
    if(a+b>=10 & a+b<=20)  return  true;
    else return false;
}
public static  void Positive(int a){
if (a>=0) System.out.println("Positive");
else System.out.println("Negative");
}
public  static boolean Negative(int a){
if (a<0) return  true;
else return false;
}
public static void HighYear(int a){
    if(a%4==0 && a%100 !=0 || a%400==0) System.out.println("Высокосный год");
    else System.out.println("Не высокосный");

}

public static void Hello(String a){
    System.out.println("Привет, "+a);
}

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the name:");
        String nm=sc.nextLine();
        System.out.println("Enter the year:");
        int  y=sc.nextInt();
        System.out.println("Enter the digits:");
        int a1=sc.nextInt();
        int b1=sc.nextInt();
        int c1=sc.nextInt();
        int d1=sc.nextInt();
        Hello(nm);
        System.out.println("Sum:");
        System.out.println(Calculate(a1,b1,c1,d1));
        System.out.println("Is 1st+2nd between 10 and 20?");
        System.out.println(Check(a1,b1));
        System.out.println("Is the 1st negative?");
        System.out.println(Negative(a1));
        System.out.println("Is the 2nd positive or negative?");
        Positive(b1);
        HighYear(y);
        sc.close();
    }
}
