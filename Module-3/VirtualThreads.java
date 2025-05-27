public class VirtualThreads{

    public static void main(String[] args) throws InterruptedException {
        final int THREAD_COUNT = 100_000;

        System.out.println("Starting Virtual Threads...");

        // Virtual Threads
        long startVirtual = System.currentTimeMillis();

        Thread[] virtualThreads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            virtualThreads[i] = Thread.startVirtualThread(() -> {
                System.out.println("Hello from virtual thread " + Thread.currentThread().getName());
            });
        }
        for (Thread t : virtualThreads) {
            t.join();
        }

        long endVirtual = System.currentTimeMillis();
        System.out.println("Virtual Threads total time: " + (endVirtual - startVirtual) + " ms");

        System.out.println("\nStarting Traditional Threads...");

        // Traditional Threads
        long startTraditional = System.currentTimeMillis();

        Thread[] traditionalThreads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            traditionalThreads[i] = new Thread(() -> {
                System.out.println("Hello from traditional thread " + Thread.currentThread().getName());
            });
            traditionalThreads[i].start();
        }
        for (Thread t : traditionalThreads) {
            t.join();
        }

        long endTraditional = System.currentTimeMillis();
        System.out.println("Traditional Threads total time: " + (endTraditional - startTraditional) + " ms");
    }
}
