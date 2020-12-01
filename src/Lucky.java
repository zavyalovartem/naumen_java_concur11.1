import java.util.concurrent.atomic.AtomicInteger;

public class Lucky {
    static AtomicInteger x = new AtomicInteger(0);
    static AtomicInteger count = new AtomicInteger(0);

    static class LuckyThread extends Thread {
        @Override
        public void run() {
            while (true) {
                int newX = x.incrementAndGet();
                if (newX > 999999){
                    break;
                }
                if ((newX % 10) + (newX / 10) % 10 + (newX / 100) % 10 == (newX / 1000)
                        % 10 + (newX / 10000) % 10 + (newX / 100000) % 10) {
                    System.out.println(newX);
                    count.incrementAndGet();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new LuckyThread();
        Thread t2 = new LuckyThread();
        Thread t3 = new LuckyThread();
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("Total: " + count.get());
    }
}