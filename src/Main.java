import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> future = executor.submit(() -> {

            System.out.println("Starting...");

            Random random = new Random();
            Integer duration = random.nextInt(4000);

            if (duration > 1000){
                throw new IOException("sleeping for too long!");
            }

            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished!");

            return duration;
        });

        executor.shutdown();

        try {
            System.out.println("future: " + future.get().toString());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
