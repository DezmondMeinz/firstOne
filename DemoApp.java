package ru.geekbrains.homework;

public class DemoApp {

    public static void main(String[] args) {
        int result = 0;

        String[][] array = {{"1","1","1","1"},{"1","1","1","1"}};
        String[][] error_array = {{"1","2","3","4","1"},{"1","2","3","4"}};
        String[][] error_data = {{"1","1","3","4",},{"1","2","аа3","4"}};

        System.out.println("Начало  программы");
        System.out.println("=======================");

        System.out.println("Корректный массива");
        try {
            result = 0;
            result = analyze(array);
        } catch(ArrSizeException e){
            System.out.println(e.getMessage());
        } catch(ArrDataException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Результат суммирования элементов массива равна "+String.valueOf(result));
            System.out.println("=======================");
        }

        System.out.println("Случай некорректного массива");
        try {
            result = 0;
            result = analyze(error_array);
        } catch(ArrSizeException e){
            System.out.println(e.getMessage());
        } catch(ArrDataException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Результат суммирования элементов массива равна "+String.valueOf(result));
            System.out.println("=======================");
        }

        System.out.println("Случай некорректных данных");
        try {
            result = 0;
            result = analyze(error_data);
        } catch(ArrSizeException e){
            System.out.println(e.getMessage());
        } catch(ArrDataException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Результат суммирования элементов массива равна "+String.valueOf(result));
            System.out.println("=======================");
        }

        System.out.println("Работа программы завершена");
    }

    public static int analyze(String[][] array) throws ArrSizeException, ArrDataException {

        int summ = 0;
        int value = 0;
        int row = 0;
        int cell = 0;

        if(array.length != 2 || array[0].length != 4 || array[1].length != 4) {
            throw new ArrSizeException();
        }

        for(int i=1;i<3;i++){
            row = i;
            for(int c=1;c<5;c++){
                cell = c;
                try{
                    value = Integer.parseInt(array[i-1][c-1]);
                    summ += value;
                } catch (IllegalArgumentException e){
                    String message = "в "+String.valueOf(row)+" ряду, "+String.valueOf(cell)+" ячейке";
                    throw new ArrDataException(message);
                }
            }
        }

        return summ;
    }
}
