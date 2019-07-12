import java.util.concurrent.CountDownLatch;

//在完成某些运算时，只有其他的所有运算全部完成时，当前运算才会继续执行
public class TestCountDownLatch {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        LatchDemo LatchDemo = new LatchDemo(countDownLatch);

        long statt = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            new Thread(LatchDemo).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long end = System.currentTimeMillis();
        System.out.println("线程耗费时间为：" + (end - statt));

    }


}

class LatchDemo implements Runnable {

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {

        synchronized (this) {
            try {
                for (int i = 0; i < 50000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } finally {
                latch.countDown();
            }
        }


    }
}

