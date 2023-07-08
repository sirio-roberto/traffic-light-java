package traffic;

import java.util.Arrays;

public class QueueThread extends Thread {

    private int roads;
    private int interval;

    private boolean printInfo = false;

    private boolean keepRunning = true;

    private final Road[] roadArray;

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
                    updateRoadStatuses();
                }
                sleep(1000L);
                seconds++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void updateRoadStatuses() {
        for (int i = 0; i < roadArray.length; i++) {
            if (roadArray[i] != null) {
                roadArray[i].decreaseTiming();
                if (roadArray[i].isOpen() && roadArray[i].getTiming() < 1) {
                    roadArray[i].setOpen(false);
                    int nextIndex = (i + 1) % roadArray.length;
                    while (roadArray[nextIndex] == null) {
                        nextIndex = (nextIndex + 1) % roadArray.length;
                    }
                    roadArray[nextIndex].setOpen(true);
                    int timing = interval;
                    roadArray[nextIndex].setTiming(timing);
                    nextIndex = (nextIndex + 1) % roadArray.length;
                    while (true) {
                        if (roadArray[nextIndex] != null) {
                            if (roadArray[nextIndex].isOpen()) {
                                return;
                            }
                            roadArray[nextIndex].setTiming(timing);
                            timing += interval;
                        }
                        nextIndex = (nextIndex + 1) % roadArray.length;
                    }
                }
            }
        }
    }

    private String getRoadList() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < roadArray.length; i++) {
            if (roadArray[i] != null && roadArray[i].isFront()) {
                int nextIndex = i;
                while (roadArray[nextIndex] != null && !roadArray[nextIndex].isRear()) {
                    result.append(roadArray[nextIndex].getRoadStatus()).append("\n");
                    nextIndex = (nextIndex + 1) % roadArray.length;
                }
                if (roadArray[nextIndex] != null) {
                    result.append(roadArray[nextIndex].getRoadStatus()).append("\n");
                }
                return result.toString();
            }
        }
        return result.toString();
    }
}
