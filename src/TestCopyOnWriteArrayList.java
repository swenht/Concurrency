import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;



//添加操作多时，效率低，因为每次添加时就会进行复制一个完全数组，内存开销将会大，并发迭代操作多时可以选择
public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {
        HelloThread helloThread = new HelloThread();
        for (int i = 0; i < 10; i++) {
            new Thread(helloThread).start();
        }
    }
}

class HelloThread implements Runnable {

    //private static List<String> list = Collections.synchronizedList(new ArrayList<>());


    private static List<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            list.add("AA");
        }
    }
}
