import java.util.concurrent.*;
import java.util.*;

public class ExecutorServiceWithCallable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;

            Callable<String> task = () -> {
                Thread.sleep(500);
                return "Result from Task " + taskId;
            };
            Future<String> future = executor.submit(task);
            futureList.add(future);
        }
        for (Future<String> future : futureList) {
            String result = future.get();  // waits for the task to complete
            System.out.println(result);
        }
        executor.shutdown();
    }
}
