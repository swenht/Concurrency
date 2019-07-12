public class TestProducterAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Productor pro = new Productor(clerk);
        Consumer con = new Consumer(clerk);
        new Thread(pro, "生产者A").start();
        new Thread(con, "消费者B").start();
    }
}


class Clerk {
    private int product = 0;

    public synchronized void get() {
        if (product >= 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("产品已满！");
        } else {
            System.out.println(Thread.currentThread().getName() + ":" + ++product);
            this.notifyAll();
        }
    }


    public synchronized void sale() {
        if (product <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("缺货！");
        } else {
            System.out.println(Thread.currentThread().getName() + ":" + --product);
            this.notifyAll();
        }
    }
}


class Productor implements Runnable {
    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.get();
        }
    }
}


class Consumer implements Runnable {

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            clerk.sale();
        }
    }
}