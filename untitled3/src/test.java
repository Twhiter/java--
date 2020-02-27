import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author T.Whiter
 * @Date 2020/2/25 14:40
 * @Version 1.0
 */
public class test {


    final int x;
    int y;
    static test t;

    public test() {
        x = 1;
        y = 4;
    }

    static void write() {
        t = new test();
    }

    static void read() {
        if (t != null) {
            int i = t.x;
            int j = t.y;

            System.out.println("x:" + i);
            System.out.println("y:" + j);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ArrayList<Thread> thread = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            if (i <= 3)
                thread.add(new Thread(test::write));
            else thread.add(new Thread(test::read));
        }


        Collections.shuffle(thread);

        for (int i = 0; i < 10; i++) {
            thread.get(i).start();
        }

        for (int i = 0; i < 10; i++) {
            thread.get(i).join();
        }

    }
}
