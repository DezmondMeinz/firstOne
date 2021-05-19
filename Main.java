package ru.geekbrains.homework;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> playerScores = getPlayerScores(scanner);
        List<Integer> aliceScores = getAliceScores(scanner);

        Set<Integer> uniqueScores = new LinkedHashSet<>(playerScores);
        playerScores = new ArrayList<>(uniqueScores);

        int i = playerScores.size() - 1;
        for (Integer oneScore : aliceScores) {
            while (i >= 0) {
                if (oneScore < playerScores.get(i)) {
                    System.out.println(i + 2);
                    break;
                }
                i--;
            }
            if (i < 0) {
                System.out.println(1);
            }
        }
    }

    private static List<Integer> getAliceScores(Scanner scanner) {
        return getIntegers(scanner);
    }

    private static List<Integer> getPlayerScores(Scanner scanner) {
        return getIntegers(scanner);
    }


    private static List<Integer> getIntegers(Scanner scanner) {
        int size = scanner.nextInt();
        List<Integer> integers = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            integers.add(scanner.nextInt());
        }
        return integers;
    }
}
