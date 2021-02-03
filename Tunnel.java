package ru.geekbrains.homework;

import java.util.concurrent.Semaphore;



public class Tunnel extends Stage {

    public Tunnel() {
        this.length = 80;
        this.description = "Tunnel " + length + "meters";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " prepairing(waiting): " + description);
               // smp.acquire();
                System.out.println(c.getName() + " start: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " finish: " + description);
                //smp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
