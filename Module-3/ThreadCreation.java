class MyThread extends Thread {
    String message;

    MyThread(String message) {
        this.message = message;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(message + " - " + i);
        }
    }
}

public class ThreadCreation
{
    public static void main(String[] args) {
        MyThread thread1 = new MyThread("Thread One");
        MyThread thread2 = new MyThread("Thread Two");

        thread1.start();
        thread2.start();
    }
}
