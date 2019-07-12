import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestAbc {
    public static void main(String[] args) {
        AlternateDemo demo = new AlternateDemo();
        new Thread(() -> {
            for (int i = 1; i < 20; i++) {
                demo.loopA(i);

            }
        });

        new Thread(() -> {
            for (int i = 1; i < 20; i++) {
                demo.loopB(i);

            }
        });


        new Thread(() -> {
            for (int i = 1; i < 20; i++) {
                demo.loopC(i);

            }
        });


    }
}


class AlternateDemo {
    private int number = 1;//当前正在执行线程的标记

    private Lock lock = new ReentrantLock();

    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(int totalLoop) {
        lock.lock();

        try {
            if (number != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "i\t" + totalLoop);
            }

            number = 2;
            condition2.signal();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void loopB(int totalLoop) {
        lock.lock();

        try {
            if (number != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "i\t" + totalLoop);
            }

            number = 3;
            condition3.signal();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void loopC(int totalLoop) {
        lock.lock();

        try {
            if (number != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 25; i++) {
                System.out.println(Thread.currentThread().getName() + "i\t" + totalLoop);
            }

            number = 1;
            condition1.signal();


        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
