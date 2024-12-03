import esgi.fyc.ReentrantLockExample;

void main() {
    ReentrantLockExample lockExample = new ReentrantLockExample();

    Runnable task = () -> {
        for (int i = 0; i < 5; i++) {
            lockExample.increment();
        }
    };

    Thread t1 = new Thread(task, "Thread 1");
    Thread t2 = new Thread(task, "Thread 2");
    Thread t3 = new Thread(task, "Thread 3");

    t1.start();
    t2.start();
    t3.start();

    try {
        t1.join();
        t2.join();
        t3.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    System.out.println("Valeur finale du compteur : " + lockExample.getCount());
}
