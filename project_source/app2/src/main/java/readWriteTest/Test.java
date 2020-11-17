package test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


public class Test {
    final Object mLock = new Object();
    //��
    //static ArrayList<Integer> a = new ArrayList<Integer>();
    static List<Integer> a = new CopyOnWriteArrayList<>();

    //���� ArrayList / CopyOnWriteArrayList


    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            a.add(i);

        }
        long mainstart = System.nanoTime();
        for (int i = 0; i < 4; i++) {

            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(0);
                    // set();
                    // setlock();
                    read();
                    //���� set() =add�׽�Ʈ  setlock(); add,lock�׽�Ʈ
                    //read= get�׽�Ʈ

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.start();
            threads.add(t);
        }

        for (int i = 0; i < threads.size(); i++) {
            Thread t = threads.get(i);
            try {
                t.join();
            } catch (Exception e) {
            }
        }

        System.out.println("------------------------.");
        System.out.println("total-------" + ((System.nanoTime() - mainstart) / Math.pow(10, 7)));
    }


    public static void addd() {
        long start = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            a.add(i);
        }
        long end = System.nanoTime();

        System.out.println("write-" + ((end - start) / Math.pow(10, 7)));

    }


    public static void setlock() {
        synchronized (a) {
            addd();

        }
    }

    public static void set() {

        addd();

    }

    public static void read() {

        long start = System.nanoTime();
        for (int i = 0; i < a.size(); i++) {
            a.get(i);
        }
        long end = System.nanoTime();
        System.out.println("read-" + ((end - start) / Math.pow(10, 7)));
    }


}