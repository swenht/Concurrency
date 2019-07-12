import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {
    public static void main(String[] args) {
        ThreadDemo2 td = new ThreadDemo2();
        FutureTask<Integer> result = new FutureTask<>(td);
        new Thread(result).start();


        int i = 0;
        try {
            i = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }
}

class ThreadDemo2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 1000000; i++) {
            System.out.println(i);
            sum += i;
        }
        return sum;
    }
}