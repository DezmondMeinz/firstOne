package ru.geekbrains.homework;

public class Main {


    public static void main(String[] args) {
        Person[] persArray = new Person[5];
        persArray[0] = new Person("Ivanov Ivan", "Director", "ivivan@mailbox.com", "892312312", 30000, 30);
        persArray[1] = new Person("Sokova Tanya", "Employee1", "sokova@mailbox.com", "1892312312", 41000, 40);
        persArray[2] = new Person("Volodina Gera", "Employee2", "volod@mailbox.com", "2892312312", 52000, 50);
        persArray[3] = new Person("Petrov Leonid", "Employee3", "petr@mailbox.com", "3892312312", 60000, 60);
        persArray[4] = new Person("Pushkin Alex", "Employee4", "push@mailbox.com", "4892312312", 90000, 70);

        for(Person item : persArray) {
            if(item.GetAge() > 40) item.Show();
        }

    }
}
