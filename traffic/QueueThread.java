package traffic;

public class QueueThread extends Thread {

    private int roads;
    private int interval;

    private boolean printInfo = false;

    private boolean keepRunning = true;

    public QueueThread(String name, int roads, int interval) {
        super(name);
        this.roads = roads;
        this.interval = interval;
    }

    public void setPrintInfo(boolean printInfo) {
        this.printInfo = printInfo;
    }

    public void setKeepRunning(boolean keepRunning) {
        this.keepRunning = keepRunning;
    }

    @Override
    public void run() {
        long seconds = 1;
        while (keepRunning) {
            try {
                if (printInfo) {
                    Main.clearMenu();
                    System.out.printf("""
                        ! %ds. have passed since system startup !
                        ! Number of roads: %d !
                        ! Interval: %d !
                        ! Press "Enter" to open menu !
                        """, seconds, roads, interval);
                }
                sleep(1000L);
                seconds++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
