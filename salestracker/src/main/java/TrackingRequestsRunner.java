import java.util.concurrent.*;

public class TrackingRequestsRunner {
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for(int i = 0; i < 100000; i++) {
            Runnable trackingRequest = new TrackingRequest();
            executor.execute(trackingRequest);
        }

        executor.shutdown();
    }
}
