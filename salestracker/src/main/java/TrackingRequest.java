public class TrackingRequest implements Runnable {

    @Override
    public void run() {
        SalesTracker.track().forEach(salesSummary -> {
            System.out.println(salesSummary);
        });
    }

}
