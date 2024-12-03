package esgi.fyc;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class ReentrantLockExample {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;

    public void increment() {
        System.out.println(Thread.currentThread().getName() + " tente d'acquérir le verrou...");
        try {
            if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                try {
                    count++;
                    System.out.println(Thread.currentThread().getName() + " a incrémenté le compteur : " + count);
                } finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                        System.out.println(Thread.currentThread().getName() + " a libéré le verrou.");
                    }
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " n'a pas pu obtenir le verrou après 500ms.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return count;
    }

}