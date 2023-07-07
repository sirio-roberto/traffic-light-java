package traffic;

public class QueueThread extends Thread {

    private int roads;
    private int interval;

    private boolean printInfo = false;

    private boolean keepRunning = true;

    private Road[] roadArray;

    public QueueThread(String name, int roads, int interval, Road[] roadArray) {
        super(name);
        this.roads = roads;
        this.interval = interval;
        this.roadArray = roadArray;
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
                        
                        %s
                        ! Press "Enter" to open menu !
                        """, seconds, roads, interval, getRoadList());
                }
                sleep(1000L);
                seconds++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private String getRoadList() {
        StringBuilder result = new StringBuilder();
        for (Road road: roadArray) {
            if (road != null) {
                result.append(road.getName()).append("\n");
            }
        }
        return result.toString();
    }
}
