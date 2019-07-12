import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicDemo {
    public static void main(String[] args) {
        AtmonicDemo AtmonicDemo = new AtmonicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(AtmonicDemo).start();
        }
    }
}

class AtmonicDemo implements Runnable {
    private AtomicInteger sernalNumber = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ":" + getSernalNumber());
    }

    public int getSernalNumber() {
        return sernalNumber.getAndIncrement();
    }
}
