public class TestCAS {
    public static void main(String[] args) {

        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int value = cas.get();
                boolean flag = cas.compareAndSet(value, (int) (Math.random() * 101));
                System.out.println(flag);
            });
        }
    }
}

class CompareAndSwap {
    private int value = 0;


    public synchronized int get() {
        return this.value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = this.value;
        if (oldValue == expectedValue) {
            this.value = newValue;
        }
        return oldValue;
    }


    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }

}