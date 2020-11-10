package ru.geekbrains.homework;
import java.util.Random;
import java.util.Scanner;

public class TicTac {
    public static int size;
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static boolean isMapFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static boolean check(char symb) {
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (size - 1 - i == j && map[i][j] == symb) {
                    counter++;
                }
            }

        }
        if(counter==size){
            return true;
        }
        else {
            counter = 0;
        }


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j && map[i][j] == symb) {
                    counter++;
                }
            }
        }
        if(counter==size){
            return true;
        }
        else {
            counter = 0;
        }

        for (int i = 0; i < size; i++) {  //lines
            for (int j = 0; j < size; j++) {
                if(map[i][j] == symb) {
                    counter++;

                }
            }
            if(counter==size){
                return true;
                //break;
            }
            else {
                counter = 0;
            }
        }
        counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[j][i] == symb) {
                    counter++;
                }
            }
            if(counter==size){
                return true;
                //break;
            }
            else {
                counter = 0;
            }
        }
        return false;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(size);
            y = rand.nextInt(size);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); //
        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void initMap() {
        map = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Введите размерность поля (от 1 до ...)");
        size=sc.nextInt();
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (check(DOT_X)) {
                System.out.println("Победил кожанный мешок!! ");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (check(DOT_X)) {
                System.out.println("Победил Скайнет))");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
        sc.close();
    }
}
